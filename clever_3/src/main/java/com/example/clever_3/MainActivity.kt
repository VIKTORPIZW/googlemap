package com.example.clever_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clever_3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSelect.setOnClickListener {

        supportFragmentManager.beginTransaction().replace(
            R.id.firstFragmentContainer, ListFragment()
        ).commit()
        }
    }
}