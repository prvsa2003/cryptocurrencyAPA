package com.example.cryptocurrency_apa.Activitys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.cryptocurrency_apa.R
import com.example.cryptocurrency_apa.apiManajer.ALL
import com.example.cryptocurrency_apa.apiManajer.Api_Manajer
import com.example.cryptocurrency_apa.apiManajer.HOUR
import com.example.cryptocurrency_apa.apiManajer.HOURS24
import com.example.cryptocurrency_apa.apiManajer.MONTH
import com.example.cryptocurrency_apa.apiManajer.MONTH3
import com.example.cryptocurrency_apa.apiManajer.WEEK
import com.example.cryptocurrency_apa.apiManajer.YEAR
import com.example.cryptocurrency_apa.apiManajer.model.ChartData
import com.example.cryptocurrency_apa.apiManajer.model.CoinAboutItem
import com.example.cryptocurrency_apa.databinding.ActivityCoinBinding
import ir.dunijet.dunipool.apiManager.Model.Data_Coin

class CoinActivity : AppCompatActivity() {
    var apiManager= Api_Manajer()
    private lateinit var binding: ActivityCoinBinding
    private lateinit var  dataCoin: Data_Coin.Data
    private lateinit var datathiscoin : CoinAboutItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fromintent = intent.getBundleExtra("bundel")
        dataCoin = fromintent!!.getParcelable("bundel1")!!
        if(fromintent.getParcelable<CoinAboutItem>("bundel2" )!=null){
            datathiscoin = fromintent.getParcelable("bundel2")!!
        }else{
            datathiscoin = CoinAboutItem()
        }
        binding.toolbarcoinpage.toolbar.title = dataCoin.coinInfo.name



        initUi()
    }

    private fun initUi() {
        chart()
        static()
        about()
    }

    private fun about() {
       binding.layoutabout.madoulOfCardOfAbout.webtext.text = datathiscoin.websit
        binding.layoutabout.madoulOfCardOfAbout.twxtxt.text = "@"+datathiscoin.twiter
        binding.layoutabout.madoulOfCardOfAbout.githubtxt.text = datathiscoin.github
        binding.layoutabout.madoulOfCardOfAbout.rddittxt.text = datathiscoin.redite
        binding.layoutabout.madoulAboutText.aboutbigtext.text = datathiscoin.dacioment
        binding.layoutabout.madoulOfCardOfAbout.webtext.setOnClickListener {
            open(datathiscoin.websit!!)
        }
        binding.layoutabout.madoulOfCardOfAbout.twxtxt.setOnClickListener {
            open("https://twitter.com/"+datathiscoin.twiter!!)
        }
        binding.layoutabout.madoulOfCardOfAbout.githubtxt.setOnClickListener {
            open(datathiscoin.github!!)
        }
        binding.layoutabout.madoulOfCardOfAbout.rddittxt.setOnClickListener {
            open(datathiscoin.redite!!)
        }
    }

    private fun open(url : String) {
       val intent = Intent(Intent.ACTION_VIEW , Uri.parse(url))
        startActivity(intent)
    }

    private fun static() {
      binding.layoutstatic.madoulcardstatitstc.txtNumberOpen.text = dataCoin.dISPLAY.uSD.oPEN24HOUR
      binding.layoutstatic.madoulcardstatitstc.txtHighDayNumbr.text = dataCoin.dISPLAY.uSD.hIGH24HOUR
      binding.layoutstatic.madoulcardstatitstc.txtLowdayNumber.text =dataCoin.dISPLAY.uSD.lOW24HOUR
      binding.layoutstatic.madoulcardstatitstc.txtChangeTodaysNumber.text = dataCoin.dISPLAY.uSD.cHANGE24HOUR
      binding.layoutstatic.madoulcardstatitstc.txtAlghrithemNumber.text = dataCoin.coinInfo.algorithm
      binding.layoutstatic.madoulcardstatitstc.totalnum.text = dataCoin.dISPLAY.uSD.tOTALTOPTIERVOLUME24H
      binding.layoutstatic.madoulcardstatitstc.txtMarketCapNumber.text = dataCoin.dISPLAY.uSD.mKTCAP
      binding.layoutstatic.madoulcardstatitstc.yxySupplyNumber.text = dataCoin.dISPLAY.uSD.sUPPLY
    }

    private fun chart() {
        var period : String = HOUR
        requestforchartdata(period)
        binding.layoutchart.radiogroupchart.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radiobutton12h -> {period = HOUR}
                R.id.radiobotton1d ->{period = HOURS24}
                R.id.radiobottn1w ->{period = WEEK}
                R.id.radiobotten1M->{period = MONTH}
                R.id.radiobotton3M->{period = MONTH3}
                R.id.radiobottn1Y->{period = YEAR}
                R.id.radiobottenAll ->{period = ALL}
            }
            requestforchartdata(period)
        }
        binding.layoutchart.PriceCounChart.Coinprice.text = dataCoin.dISPLAY.uSD.pRICE
        binding.layoutchart.madoulChartChange1.txtChange1Chart.text = " "+dataCoin.dISPLAY.uSD.cHANGE24HOUR
//        binding.layoutchart.madoulChartChange2.txtchartchange2.text = dataCoin.dISPLAY.uSD.cHANGEPCT24HOUR.toString().substring(0,8)+"%"
        val taghieer = dataCoin.rAW.uSD.cHANGEPCT24HOUR
        if (taghieer>0){
            binding.layoutchart.madoulChartChange2.txtchartchange2.setTextColor(ContextCompat.getColor(this , R.color.colorGain))
            binding.layoutchart.UpDownSign.upDownSighn.setTextColor(ContextCompat.getColor(this , R.color.colorGain))
            binding.layoutchart.UpDownSign.upDownSighn.text = "▲"
            binding.layoutchart.sparkCoin.lineColor = ContextCompat.getColor(this,R.color.colorGain)
        }else if ( taghieer<0){
            binding.layoutchart.madoulChartChange2.txtchartchange2.setTextColor(ContextCompat.getColor(this , R.color.colorLoss))
            binding.layoutchart.UpDownSign.upDownSighn.setTextColor(ContextCompat.getColor(this , R.color.colorLoss))
            binding.layoutchart.UpDownSign.upDownSighn.text = "▼"
            binding.layoutchart.sparkCoin.lineColor = ContextCompat.getColor(this,R.color.colorLoss)
        }
        binding.layoutchart.sparkCoin.setScrubListener {
            if (it == null ){
                binding.layoutchart.PriceCounChart.Coinprice.text = dataCoin.dISPLAY.uSD.pRICE
            }else{
                binding.layoutchart.PriceCounChart.Coinprice.text = "$"+(it as ChartData.Data).close.toString()
            }
        }
    }

    private fun requestforchartdata(period: String) {
    apiManager.getchartdata(dataCoin.coinInfo.name , period , object :Api_Manajer.ApiCallBack<Pair<List<ChartData.Data>,ChartData.Data?>>{
        override fun onsucces(data: Pair<List<ChartData.Data>, ChartData.Data?>) {
        val chartadapter = Chart_Adapter(data.first , data.second?.open.toString())
            binding.layoutchart.sparkCoin.adapter = chartadapter

        }

        override fun onErorr(erorrmassage: String) {
            Toast.makeText(this@CoinActivity, "erorr = " + erorrmassage!!, Toast.LENGTH_SHORT).show()
        }

    })

    }
}