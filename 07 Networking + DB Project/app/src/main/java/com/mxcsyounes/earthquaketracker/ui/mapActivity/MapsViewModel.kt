package com.mxcsyounes.earthquaketracker.ui.mapActivity

import android.app.Activity
import android.app.Application
import androidx.lifecycle.*
import com.google.android.gms.maps.model.LatLng
import com.mxcsyounes.earthquaketracker.api.models.geonames.APIResult
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake
import com.mxcsyounes.earthquaketracker.repositories.EarthquakeRepository

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    private val earthquakeRepository: EarthquakeRepository = EarthquakeRepository(application)

    fun getEarthquakeFromLatLong(latLng: LatLng , owner: LifecycleOwner): LiveData<List<Earthquake>> {

        val data = MutableLiveData<List<Earthquake>>()
        earthquakeRepository.getEarthquakesFromLatLong(latLng)
            .observe(owner, Observer<APIResult> {
                data.value = it.earthquakes
                earthquakeRepository.putEarthquakes(it.earthquakes)
            })

        return data
    }
}
