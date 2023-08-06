package com.example.cryptocurrency_apa.apiManajer

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







    interface ApiCallBack<T> {
        fun onsucces(data: T)
        fun onErorr(erorrmassage: String)
    }
}