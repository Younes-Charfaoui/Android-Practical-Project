package com.mxcsyounes.earthquaketracker.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake
import com.mxcsyounes.earthquaketracker.dataLayers.NetworkLayer
import com.mxcsyounes.earthquaketracker.database.EarthquakeDatabase
import com.mxcsyounes.earthquaketracker.database.dao.EarthquakeDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EarthquakeRepository(application: Application) {

    private var earthquakeDAO: EarthquakeDAO
    var allEarthquake: LiveData<List<Earthquake>>
    private val networkLayer = NetworkLayer()

    init {
        val database = EarthquakeDatabase.getInstance(application)
        earthquakeDAO = database.earthquakeDao()
        allEarthquake = earthquakeDAO.getAllEarthquake()
    }

    fun getEarthquakesFromLatLong(latLng: LatLng) =
        networkLayer.searchByLatLang(latLng.latitude, latLng.longitude)

    fun putEarthquakes(earthquakes: List<Earthquake>) {
        CoroutineScope(Dispatchers.IO).launch {
            val number = earthquakeDAO.deleteAll()
            Log.d("TAGME", "result was $number")
            earthquakeDAO.insertAll(earthquakes)
        }
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            earthquakeDAO.deleteAll()
        }
    }

}

