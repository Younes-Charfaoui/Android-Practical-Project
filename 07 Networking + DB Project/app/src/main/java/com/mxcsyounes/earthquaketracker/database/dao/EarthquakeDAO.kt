package com.mxcsyounes.earthquaketracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake

@Dao
interface EarthquakeDAO {

    @Query("SELECT * FROM earthquakes ORDER BY magnitude ASC")
    fun getAllEarthquake(): LiveData<List<Earthquake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(earthquake: List<Earthquake>)

    @Delete
    suspend fun delete(earthquake: Earthquake)

    @Query("DELETE FROM earthquakes")
    suspend fun deleteAll(): Int
}