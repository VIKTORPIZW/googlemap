package com.example.clever_4.data

import com.google.gson.annotations.SerializedName

data class AtmListResponse(val items:List<AtmListItem>)

 data class AtmListItem(
     @SerializedName("id")
     val atmId:String,
     @SerializedName("area")
     val area:String,
     @SerializedName("city_type")
     val city_type:String,
     @SerializedName("city")
     val city:String,
     @SerializedName("address_type")
     val address_type:String,
     @SerializedName("address")
     val address:String,
     @SerializedName("house")
     val house:String,
     @SerializedName("install_place")
     val install_place:String,
     @SerializedName("work_time")
     val work_time:String,
     @SerializedName("gps_x")
     val gps_x:String,
     @SerializedName("gps_y")
     val gps_y:String,
     @SerializedName("install_place_full")
     val install_place_full:String,
     @SerializedName("work_time_full")
     val work_time_full:String,
     @SerializedName("ATM_type")
     val atm_type:String,
     @SerializedName("ATM_error")
     val atm_error:String,
     @SerializedName("currency")
     val currency:String,
     @SerializedName("cash_in")
     val cash_in:String,
     @SerializedName("ATM_printer")
     val atm_printer:String
 )