package com.mxcsyounes.earthquaketracker.api.services

import com.mxcsyounes.earthquaketracker.api.models.usgs.APIResult
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface EarthquakeUSGSService {

    companion object {
        private const val BASE_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/"
        private const val ALL_HOUR = BASE_URL + "all_hour.geojson"

        val retrofit: Retrofit = RetrofitUtils.createRetrofit(BASE_URL)
    }

    @GET(ALL_HOUR)
    fun getRecentEarthquakeAsync(): Deferred<APIResult>
}