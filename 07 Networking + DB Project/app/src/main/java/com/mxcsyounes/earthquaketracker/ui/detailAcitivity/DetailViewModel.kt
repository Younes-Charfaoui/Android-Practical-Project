package com.mxcsyounes.earthquaketracker.ui.detailAcitivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake
import com.mxcsyounes.earthquaketracker.repositories.EarthquakeRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val earthquakeRepository: EarthquakeRepository = EarthquakeRepository(application)

    fun getAllData(): LiveData<List<Earthquake>> {
        return earthquakeRepository.allEarthquake
    }

}