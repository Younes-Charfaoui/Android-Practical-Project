package com.mxcsyounes.earthquaketracker.api.services

import com.mxcsyounes.earthquaketracker.api.models.geonames.APIResult
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeGeoNamesService {

    companion object {
        private const val BASE_URL = "http://api.geonames.org/earthquakesJSON"

        val retrofit: Retrofit = RetrofitUtils.createRetrofit(BASE_URL)
    }

    @GET(BASE_URL)
    fun getEarthquakeByLatLangAsync(
        @Query("north") north: Double,
        @Query("south") south: Double,
        @Query("east") east: Double,
        @Query("west") west: Double,
        @Query("username") username: String = "mxcsyounes",
        @Query("maxRows") maxRows: Int = 10
    ): Deferred<APIResult>
}