package com.example.cleanarcroomnote_xml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cleanarcroomnote_xml.R
import com.example.cleanarcroomnote_xml.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}