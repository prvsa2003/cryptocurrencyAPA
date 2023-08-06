package com.example.cryptocurrency_apa.apiManajer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class CoinAboutItem (
        val websit : String? = "no-data",
        val twiter : String?= "no-data" ,
        val redite : String?= "no-data" ,
        val github : String?= "no-data" ,
        val dacioment : String?= "no-data"

        ):Parcelable