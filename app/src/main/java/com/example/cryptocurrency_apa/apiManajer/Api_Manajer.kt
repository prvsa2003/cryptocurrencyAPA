package com.example.cryptocurrency_apa.apiManajer

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








    interface ApiCallBack<T> {
        fun onsucces(data: T)
        fun onErorr(erorrmassage: String)
    }
}