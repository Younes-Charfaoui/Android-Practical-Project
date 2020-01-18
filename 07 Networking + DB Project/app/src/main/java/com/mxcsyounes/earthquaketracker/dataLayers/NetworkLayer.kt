package com.mxcsyounes.earthquaketracker.dataLayers

import com.mxcsyounes.earthquaketracker.api.services.EarthquakeUSGSService

class NetworkLayer {

    private val webService: EarthquakeUSGSService = EarthquakeUSGSService.retrofit
        .create(EarthquakeUSGSService::class.java)

    suspend fun searchBestCompany() = webService.getRecentEarthquakeAsync().await()

}
