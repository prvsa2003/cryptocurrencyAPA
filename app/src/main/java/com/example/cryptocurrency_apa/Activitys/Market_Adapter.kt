package com.example.cryptocurrency_apa.Activitys

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrency_apa.R
import com.example.cryptocurrency_apa.apiManajer.BASE_URL_IMAGE
import com.example.cryptocurrency_apa.databinding.ItemRecyclerBinding
import ir.dunijet.dunipool.apiManager.Model.Data_Coin

class Market_Adapter(val data: ArrayList<Data_Coin.Data>,
                     val recyclerCallBack: RecyclercallBack) :
    RecyclerView.Adapter<Market_Adapter.viewholder>() {
    private lateinit var binding: ItemRecyclerBinding

    inner class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun binfviews(dataCoin: Data_Coin.Data) {
            binding.coinName.text = dataCoin.coinInfo.fullName
            binding.priceCoin.text = dataCoin.dISPLAY.uSD.pRICE.toString()
            val taghir = dataCoin.rAW.uSD.cHANGEPCT24HOUR
            if (taghir > 0) {
                binding.darsadTaghieer.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorGain
                    )
                )
                val taghiirat = dataCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 7) + "%"
                binding.darsadTaghieer.text = taghiirat
            } else if (taghir < 0) {
                binding.darsadTaghieer.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorLoss
                    )
                )
                val taghiier = dataCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 8) + "%"
                binding.darsadTaghieer.text = taghiier
            } else {
                binding.darsadTaghieer.text = "0"+"%"
                binding.darsadTaghieer.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.secondaryTextColor
                    )
                )
            }
            val marketcap = dataCoin.rAW.uSD.mKTCAP / 1000000000
            val indexdot = marketcap.toString().indexOf(".")
            binding.marketCap.text =
                "$" + dataCoin.rAW.uSD.mKTCAP.toString().substring(0, indexdot+3) + "B"
            Glide.with(itemView.context)
                .load(BASE_URL_IMAGE + dataCoin.coinInfo.imageUrl)
                .into(binding.imgCoins)

            itemView.setOnClickListener {
                recyclerCallBack.oncoinitemcliked(dataCoin)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val infelater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerBinding.inflate(infelater, parent, false)
        return viewholder(binding.root)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.binfviews(data[position])
    }

    interface RecyclercallBack {
        fun oncoinitemcliked(data: Data_Coin.Data)
    }


}

