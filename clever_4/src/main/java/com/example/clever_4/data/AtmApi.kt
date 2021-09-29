package com.example.clever_4.data

import io.reactivex.Single
import retrofit2.http.GET

interface AtmApi {
    @GET("./api/atm?city=Гомель")
    fun getATMList(): Single<AtmListResponse>

    }
