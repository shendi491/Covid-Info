package com.broadbandmultimedia.covidinfo.api

import com.broadbandmultimedia.covidinfo.IndonesiaResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("indonesia")
    fun getIndonesia(): Call<ArrayList<IndonesiaResponse>>
}