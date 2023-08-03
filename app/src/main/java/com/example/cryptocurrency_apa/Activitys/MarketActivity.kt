package com.example.cryptocurrency_apa.Activitys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cryptocurrency_apa.apiManajer.Api_Manajer
import com.example.cryptocurrency_apa.databinding.ActivityMarketBinding

class MarketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMarketBinding
     val apiManajer = Api_Manajer()
    lateinit var data_news :ArrayList<Pair<String,String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarmarket.toolbar.title = "Market"
        initUi()
    }

    private fun initUi() {
        getnewsfromApi()
    }
    private fun getnewsfromApi() {
        apiManajer.getNews(object :Api_Manajer.ApiCallBack<ArrayList<Pair<String,String>>>{
            override fun onsucces(data: ArrayList<Pair<String, String>>) {
                data_news = data
                refcresh()
            }

            override fun onErorr(erorrmassage: String) {
                Toast.makeText(this@MarketActivity, "erorr : " + erorrmassage, Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun refcresh() {
        val randomdata = (0..49).random()
        binding.layoutNews.txtNews.text = data_news[randomdata].first
        binding.layoutNews.imgNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(data_news[randomdata].second))
            startActivity(intent)
        }
        binding.layoutNews.txtNews.setOnClickListener {
            refcresh()
        }
    }


}