package com.example.challangechapter5.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challangechapter5.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}