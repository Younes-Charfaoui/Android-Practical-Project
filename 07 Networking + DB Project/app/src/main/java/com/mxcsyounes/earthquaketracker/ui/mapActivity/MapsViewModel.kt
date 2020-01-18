package com.mxcsyounes.earthquaketracker.ui.mapActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mxcsyounes.earthquaketracker.repositories.EarthquakeRepository

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    private val earthquakeRepository: EarthquakeRepository = EarthquakeRepository(application)



}