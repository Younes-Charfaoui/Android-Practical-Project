package com.mxcsyounes.earthquaketracker.dataLayers

import com.mxcsyounes.earthquaketracker.api.services.EarthquakeGeoNamesService
import com.mxcsyounes.earthquaketracker.api.services.EarthquakeUSGSService

class NetworkLayer {

    private val webUSGSService: EarthquakeUSGSService = EarthquakeUSGSService.retrofit
        .create(EarthquakeUSGSService::class.java)

    private val webGeoNamesService: EarthquakeGeoNamesService = EarthquakeGeoNamesService.retrofit
        .create(EarthquakeGeoNamesService::class.java)

    suspend fun searchBestCompany() = webUSGSService.getRecentEarthquakeAsync().await()

    //Latitude horizonal
    suspend fun searchByLatLang(latitude: Double, longitude: Double) =
        webGeoNamesService.getEarthquakeByLatLangAsync(
            latitude + 1,
            latitude - 1,
            longitude + 1,
            longitude - 1
        ).await()

}
