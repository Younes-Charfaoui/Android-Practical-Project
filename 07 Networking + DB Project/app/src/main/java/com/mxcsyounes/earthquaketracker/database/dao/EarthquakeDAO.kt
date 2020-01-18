package com.mxcsyounes.earthquaketracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake

interface EarthquakeDAO {

    @Query("SELECT * FROM earthquakes")
    fun getAllEarthquake(): LiveData<List<Earthquake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg earthquake: Earthquake)

    @Delete
    fun delete(earthquake: Earthquake)

    @Query("DELETE FROM earthquakes")
    fun deleteAll()
}