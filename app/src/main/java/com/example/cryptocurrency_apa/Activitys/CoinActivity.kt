package com.example.cryptocurrency_apa.Activitys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptocurrency_apa.apiManajer.model.CoinAboutItem
import com.example.cryptocurrency_apa.databinding.ActivityCoinBinding
import ir.dunijet.dunipool.apiManager.Model.Data_Coin

class CoinActivity : AppCompatActivity() {
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

    }
}