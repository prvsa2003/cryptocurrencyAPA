package com.example.cryptocurrency_apa.Activitys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency_apa.apiManajer.Api_Manajer
import com.example.cryptocurrency_apa.databinding.ActivityMarketBinding
import ir.dunijet.dunipool.apiManager.Model.Data_Coin

class MarketActivity : AppCompatActivity(), Market_Adapter.RecyclercallBack {
    private lateinit var binding: ActivityMarketBinding
    val apiManajer = Api_Manajer()
    lateinit var data_news: ArrayList<Pair<String, String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarmarket.toolbar.title = "Market"
        initUi()
        binding.watchlist.btnshowmore.setOnClickListener {
            val url = "https://www.livecoinwatch.com/"
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(url))
            startActivity(intent)
        }
        binding.swiperefreshmain.setOnRefreshListener {
            initUi()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swiperefreshmain.isRefreshing = false
            } , 1000)
        }
    }

    override fun onResume() {
        super.onResume()
        initUi()
    }

    private fun initUi() {
        getnewsfromApi()
        getcoinsfromapi()
    }

    private fun getcoinsfromapi() {
        apiManajer.getCoin(object : Api_Manajer.ApiCallBack<List<Data_Coin.Data>> {
            override fun onsucces(data: List<Data_Coin.Data>) {
                showdatainrecyclerview(data)
            }

            override fun onErorr(erorrmassage: String) {
                Toast.makeText(this@MarketActivity, "erorr : " + erorrmassage, Toast.LENGTH_LONG)
                    .show()
            }

        })
    }

    private fun showdatainrecyclerview(data: List<Data_Coin.Data>) {
        val marketadapter = Market_Adapter(ArrayList(data), this)
        val adapteer = binding.watchlist.recyclermain
        adapteer.adapter = marketadapter
        adapteer.layoutManager = LinearLayoutManager(this)


    }

    private fun getnewsfromApi() {
        apiManajer.getNews(object : Api_Manajer.ApiCallBack<ArrayList<Pair<String, String>>> {
            override fun onsucces(data: ArrayList<Pair<String, String>>) {
                data_news = data
                refcresh()
            }

            override fun onErorr(erorrmassage: String) {
                Toast.makeText(this@MarketActivity, "erorr : " + erorrmassage, Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
    private fun refcresh() {
        val randomdata = (0..49).random()
        binding.layoutNews.txtNews.text = data_news[randomdata].first
        binding.layoutNews.imgNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data_news[randomdata].second))
            startActivity(intent)
        }
        binding.layoutNews.txtNews.setOnClickListener {
            refcresh()
        }
    }

    override fun oncoinitemcliked(data: Data_Coin.Data) {
        val intent = Intent(this, CoinActivity::class.java)
        val bundel = Bundle()
        bundel.putParcelable("bundel1", data)
        intent.putExtra("bundel", bundel)
        startActivity(intent)
    }


}