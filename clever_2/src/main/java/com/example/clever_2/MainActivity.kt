package com.example.clever_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rclNames = findViewById<RecyclerView>(R.id.rclNames)
        rclNames.setHasFixedSize(true)
        val nameList = getListOfNames()
        val descList = getListOfDescriptions()
        val namesAdapter = NameAdapter(nameList,descList)
        rclNames.adapter = namesAdapter
        rclNames.layoutManager = LinearLayoutManager(this)

    }

    private fun getListOfNames(): MutableList<String> {
        val nameList = mutableListOf<String>()
        for (i in 1..1000)
            nameList.add("Title $i")
        return nameList

    }
    private fun getListOfDescriptions(): MutableList<String> {
        val descriptionList = mutableListOf<String>()
        for (i in 1..1000)
            descriptionList.add("Description $i")
        return descriptionList

    }

}