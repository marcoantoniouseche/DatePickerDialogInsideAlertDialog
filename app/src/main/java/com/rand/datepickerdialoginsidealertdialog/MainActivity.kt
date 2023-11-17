package com.rand.datepickerdialoginsidealertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rand.datepickerdialoginsidealertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        binding.holdContainer.text = String()

        setContentView(view)
    }
}