package com.example.cryptocurrency_apa.apiManajer
import com.example.cryptocurrency_apa.apiManajer.model.ChartData
import com.example.cryptocurrency_apa.apiManajer.model.Data_News
import ir.dunijet.dunipool.apiManager.Model.Data_Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api_Service {

    @Headers(API_KEY)
    @GET("v2/news/")
    fun getTonNews(
        @Query("sortOrder") sortOrder:String = "popular"
    ): Call<Data_News>



    @Headers(API_KEY)
    @GET("top/totalvolfull")
    fun gettopCoin(
        @Query("tsym")to_symbol : String = "USD",
        @Query("limit") limit_Data : Int= 10 ,

    ):Call<Data_Coin>


    @Headers(API_KEY)
    @GET("{period}")
    fun getChartData(
        @Path("period")period : String,
        @Query("fsym")fromsymbol : String,
        @Query("limit")limit:Int,
        @Query("aggregate")aggregate:Int,
        @Query("tsym")toSymbol : String = "USD"
    ):Call<ChartData>
}