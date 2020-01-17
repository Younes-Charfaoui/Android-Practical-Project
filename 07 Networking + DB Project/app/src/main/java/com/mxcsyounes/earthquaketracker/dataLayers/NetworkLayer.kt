package com.mxcsyounes.earthquaketracker.dataLayers

import com.mxcsyounes.earthquaketracker.api.services.EarthquakeService

class NetworkLayer {

    private val webService: EarthquakeService = EarthquakeService.retrofit
        .create(EarthquakeService::class.java)

    suspend fun searchBestCompany(price: String, country: String, currency: String, userId: String) =
        webService.getRecentEarthquakeAsync().await()

}
