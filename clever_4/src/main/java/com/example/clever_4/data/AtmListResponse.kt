package com.example.clever_4.data

import com.google.gson.annotations.SerializedName

data class AtmListResponse(val items:List<AtmListItem>)

 data class AtmListItem(

     @SerializedName("gps_x")
     val gps_x:String,
     @SerializedName("gps_y")
     val gps_y:String,
 )