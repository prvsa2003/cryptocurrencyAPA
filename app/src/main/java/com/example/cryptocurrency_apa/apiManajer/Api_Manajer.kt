package com.example.cryptocurrency_apa.apiManajer

import com.example.cryptocurrency_apa.apiManajer.model.ChartData
import com.example.cryptocurrency_apa.apiManajer.model.Data_News
import ir.dunijet.dunipool.apiManager.Model.Data_Coin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api_Manajer {
    private val apiService: Api_Service
    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        apiService = retrofit.create(Api_Service::class.java)
    }



    fun getNews(apiCallBack:ApiCallBack<ArrayList<Pair<String,String>>>){
        apiService.getTonNews("popular").enqueue(object :Callback<Data_News>{
            override fun onResponse(call: Call<Data_News>, response: Response<Data_News>) {
                val data = response.body()!!
                val datatosend : ArrayList<Pair<String,String>> = arrayListOf()
                data.data.forEach{
                    datatosend.add(Pair(it.title , it.url))
                }
                apiCallBack.onsucces(datatosend)
            }

            override fun onFailure(call: Call<Data_News>, t: Throwable) {
                apiCallBack.onErorr(t.message!!)
            }

        })
    }
    fun getCoin(apicallBack: ApiCallBack<List<Data_Coin.Data>>){
        apiService.gettopCoin().enqueue(object :Callback<Data_Coin>{
            override fun onResponse(call: Call<Data_Coin>, response: Response<Data_Coin>) {
                val data = response.body()!!
                apicallBack.onsucces(data.data)
            }

            override fun onFailure(call: Call<Data_Coin>, t: Throwable) {
                apicallBack.onErorr(t.message!!)
            }

        })

    }
    fun getchartdata(symbol : String , period : String , apiCallback: ApiCallBack<Pair<List<ChartData.Data>, ChartData.Data?>>){
        var histoperiod = ""
        var limit = 30
        var aggregate = 1
        when(period){
            HOUR -> {
                histoperiod = HISTO_MINUTE
                limit = 60
                aggregate = 12
            }
            HOURS24 -> {
                histoperiod = HISTO_HOUR
                limit = 24
            }
            MONTH -> {
                histoperiod = HISTO_DAY
                limit = 30
            }
            MONTH3 -> {
                histoperiod = HISTO_DAY
                limit = 90
            }
            WEEK -> {
                histoperiod = HISTO_HOUR
                aggregate = 6
            }
            YEAR -> {
                histoperiod = HISTO_DAY
                aggregate = 13
            }
            ALL -> {
                histoperiod = HISTO_DAY
                aggregate = 30
                limit = 2000
            }
        }
        apiService.getChartData(histoperiod , symbol , limit , aggregate).enqueue(object :Callback<ChartData>{
            override fun onResponse(call: Call<ChartData>, response: Response<ChartData>) {

                val data = response.body()!!
                val data1 = data.data
                val data2 = data.data.maxByOrNull { it.close.toFloat() }
                val twodata = Pair(data1,data2)
                apiCallback.onsucces(twodata)

            }

            override fun onFailure(call: Call<ChartData>, t: Throwable) {
                apiCallback.onErorr(t.message!!)

            }

        })
    }







    interface ApiCallBack<T> {
        fun onsucces(data: T)
        fun onErorr(erorrmassage: String)
    }
}