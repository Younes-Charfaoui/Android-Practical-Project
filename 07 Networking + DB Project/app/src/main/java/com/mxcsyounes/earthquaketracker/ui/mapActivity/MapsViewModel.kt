package com.mxcsyounes.earthquaketracker.ui.mapActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.maps.model.LatLng
import com.mxcsyounes.earthquaketracker.repositories.EarthquakeRepository

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    private val earthquakeRepository: EarthquakeRepository = EarthquakeRepository(application)

    fun getEarthquakeFromLatLong(latLng: LatLng) = earthquakeRepository.getEarthquakesFromLatLong(latLng)

}