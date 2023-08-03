package com.example.cryptocurrency_apa.Activitys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrency_apa.R
import com.example.cryptocurrency_apa.apiManajer.BASE_URL_IMAGE
import com.example.cryptocurrency_apa.apiManajer.model.Data_Coin
import com.example.cryptocurrency_apa.databinding.ItemRecyclerBinding

class Market_Adapter(val data : ArrayList<Data_Coin.Data> , val recyclerCallBack : RecyclercallBack) :RecyclerView.Adapter<Market_Adapter.viewholder>(){
    private lateinit var binding:ItemRecyclerBinding
    inner class viewholder(itemview : View) :RecyclerView.ViewHolder(itemview){

        fun binfviews(dataCoin: Data_Coin.Data){
            binding.coinName.text = dataCoin.coinInfo.fullName
            binding.priceCoin.text = dataCoin.dISPLAY.uSD.pRICE
            binding.marketCap.text = (dataCoin.rAW.uSD.mKTCAP/1000000000).toString()
            val taghir = dataCoin.rAW.uSD.cHANGEPCT24HOUR
            if(taghir>0){
                binding.darsadTaghieer.setTextColor(ContextCompat.getColor(binding.root.context , R.color.colorGain))
                val taghiirat =taghir.toString().substring(0,7) + "%"
                binding.darsadTaghieer.text= taghiirat
            }else if (taghir<0){
                binding.darsadTaghieer.setTextColor(ContextCompat.getColor(binding.root.context , R.color.colorLoss))
                val taghiier = taghir.toString().toString().substring(0,8)+"%"
                binding.darsadTaghieer.text = taghiier
            }else{
                binding.darsadTaghieer.text = "0%"
                binding.darsadTaghieer.setTextColor(ContextCompat.getColor(binding.root.context , R.color.secondaryTextColor))
            }
            Glide.with(binding.root)
                .load(BASE_URL_IMAGE)
                .into(binding.imgCoins)

            itemView.setOnClickListener {
                recyclerCallBack.oncoinitemcliked(dataCoin)
            }
        }

    }








    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val infelater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerBinding.inflate(infelater)
        return viewholder(binding.root)

    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.binfviews(data[position])
    }
    interface RecyclercallBack {
        fun oncoinitemcliked(data : Data_Coin.Data)
    }
}

