package com.hfad.android.storageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.android.storageapp.databinding.ActivityMainBinding


private var _binding: ActivityMainBinding? = null
private val binding get() = requireNotNull(_binding)


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}