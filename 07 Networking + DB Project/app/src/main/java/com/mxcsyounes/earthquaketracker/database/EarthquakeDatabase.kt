package com.mxcsyounes.earthquaketracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake
import com.mxcsyounes.earthquaketracker.database.dao.EarthquakeDAO

@Database(entities = [Earthquake::class], version = 1, exportSchema = false)
abstract class EarthquakeDatabase : RoomDatabase() {

    abstract fun earthquakeDao(): EarthquakeDAO

    companion object {
        @Volatile
        private var INSTANCE: EarthquakeDatabase? = null

        fun getInstance(context: Context): EarthquakeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(EarthquakeDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EarthquakeDatabase::class.java,
                    "earthquake_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}