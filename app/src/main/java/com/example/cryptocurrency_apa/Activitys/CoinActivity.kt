package com.example.cryptocurrency_apa.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptocurrency_apa.databinding.ActivityCoinBinding
import ir.dunijet.dunipool.apiManager.Model.Data_Coin

class CoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinBinding
    private lateinit var  dataCoin: Data_Coin.Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fromintent = intent.getBundleExtra("bundel")
        dataCoin = fromintent!!.getParcelable("bundel1")!!
        binding.toolbarcoinpage.toolbar.title = dataCoin.coinInfo.name



        initUi()
    }

    private fun initUi() {
        chart()
        static()
        about()
    }

    private fun about() {


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