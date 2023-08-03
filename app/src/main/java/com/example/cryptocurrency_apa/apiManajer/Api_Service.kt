package com.example.cryptocurrency_apa.apiManajer

import com.example.cryptocurrency_apa.apiManajer.model.Data_Coin
import com.example.cryptocurrency_apa.apiManajer.model.Data_News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
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
        @Query("fsym")from_symbol : String,
        @Query("limit") limit_Data : Int= 10 ,

    ):Call<Data_Coin>
}