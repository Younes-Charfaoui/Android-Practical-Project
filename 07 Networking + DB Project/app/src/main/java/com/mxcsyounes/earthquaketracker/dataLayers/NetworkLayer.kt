package com.mxcsyounes.earthquaketracker.dataLayers

import com.mxcsyounes.earthquaketracker.api.services.EarthquakeGeoNamesService
import com.mxcsyounes.earthquaketracker.api.services.EarthquakeUSGSService
import com.mxcsyounes.earthquaketracker.utils.RetrofitLiveData

class NetworkLayer {

    private val webUSGSService: EarthquakeUSGSService = EarthquakeUSGSService.retrofit
        .create(EarthquakeUSGSService::class.java)

    private val webGeoNamesService: EarthquakeGeoNamesService = EarthquakeGeoNamesService.retrofit
        .create(EarthquakeGeoNamesService::class.java)

    fun searchBestCompany() = webUSGSService.getRecentEarthquakeAsync()

    //Latitude horizonal
    fun searchByLatLang(latitude: Double, longitude: Double) =
        RetrofitLiveData(
            webGeoNamesService.getEarthquakeByLatLang(
                latitude + 1,
                latitude - 1,
                longitude + 1,
                longitude - 1
            )
        )

}
