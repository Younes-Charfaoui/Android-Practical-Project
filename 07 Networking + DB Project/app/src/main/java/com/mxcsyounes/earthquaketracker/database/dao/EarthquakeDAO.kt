package com.mxcsyounes.earthquaketracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake

@Dao
interface EarthquakeDAO {

    @Query("SELECT * FROM earthquakes ORDER BY magnitude ASC")
    fun getAllEarthquake(): LiveData<List<Earthquake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg earthquake: Earthquake)

    @Delete
    fun delete(earthquake: Earthquake)

    @Query("DELETE FROM earthquakes")
    fun deleteAll()
}