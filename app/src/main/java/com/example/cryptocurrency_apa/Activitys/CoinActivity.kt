package com.example.cryptocurrency_apa.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptocurrency_apa.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}