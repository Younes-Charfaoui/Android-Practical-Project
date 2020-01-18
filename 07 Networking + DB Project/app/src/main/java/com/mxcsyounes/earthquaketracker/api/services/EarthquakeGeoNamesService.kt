package com.mxcsyounes.earthquaketracker.api.services

import com.mxcsyounes.earthquaketracker.api.models.usgs.APIResult
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET

interface EarthquakeGeoNamesService {

    companion object {
        private const val BASE_URL = "http://api.geonames.org/earthquakesJSON"

        val retrofit: Retrofit = RetrofitUtils.createRetrofit(BASE_URL)
    }

    @GET(BASE_URL)
    fun getRecentEarthquakeAsync(): Deferred<APIResult>
}