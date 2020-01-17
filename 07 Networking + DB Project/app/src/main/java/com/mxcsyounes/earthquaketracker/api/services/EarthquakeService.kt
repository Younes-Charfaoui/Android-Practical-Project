package com.mxcsyounes.earthquaketracker.api.services

import android.provider.ContactsContract
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mxcsyounes.earthquaketracker.api.models.APIResult
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface EarthquakeService {



    companion object {
        private const val BASE_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/"
        private const val ALL_HOUR = BASE_URL + "all_hour.geojson"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createClient())
            .build()

        private fun createClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
            return builder.build()
        }
    }

    @GET(ALL_HOUR)
    fun getRecentEarthquakeAsync() : Deferred<APIResult>
}