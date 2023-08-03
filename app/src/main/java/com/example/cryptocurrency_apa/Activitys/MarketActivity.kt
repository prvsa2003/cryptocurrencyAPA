package com.example.cryptocurrency_apa.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptocurrency_apa.apiManajer.Api_Manajer
import com.example.cryptocurrency_apa.databinding.ActivityMarketBinding

class MarketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMarketBinding
     val apiManajer = Api_Manajer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarmarket.toolbar.title = "Market"


        initUi()

    }

    private fun initUi() {
        getNews()
    }

    private fun getNews() {
        TODO("Not yet implemented")
    }
}