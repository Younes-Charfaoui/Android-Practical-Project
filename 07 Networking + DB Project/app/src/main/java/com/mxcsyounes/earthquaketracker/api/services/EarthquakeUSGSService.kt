package com.mxcsyounes.earthquaketracker.api.services

import com.mxcsyounes.earthquaketracker.api.models.usgs.APIResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface EarthquakeUSGSService {

    companion object {
        private const val BASE_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/"
        private const val ALL_HOUR = BASE_URL + "all_hour.geojson"

        val retrofit: Retrofit = RetrofitUtils.createRetrofit(BASE_URL)
    }

    @GET(ALL_HOUR)
    fun getRecentEarthquakeAsync(): Call<APIResult>
}