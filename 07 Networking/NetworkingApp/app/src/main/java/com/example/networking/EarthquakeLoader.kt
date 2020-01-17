package com.example.networking

import android.content.AsyncTaskLoader
import android.content.Context

import java.io.IOException
import java.net.URL
import java.util.ArrayList

/**
 * Created by Gosu on 2017-02-24.
 */

// Class created for using Loader instead of AsyncTask
class EarthquakeLoader// Constructor
    (
    internal var context: Context,
    internal var url: String,
    internal var earthquakes: ArrayList<Earthquake>
) : AsyncTaskLoader<ArrayList<Earthquake>>(context) {

    // Used to start the Loader
    override fun onStartLoading() {
        forceLoad()
    }

    // Work in background thread
    override fun loadInBackground(): ArrayList<Earthquake> {
        // Creating URL
        val connection = QueryUtils.createUrl(url)
        val jsonResponse: String
        try {
            // Establishing connection,
            jsonResponse = QueryUtils.makeHttpRequest(connection)
            earthquakes = QueryUtils.extractEarthquakes(jsonResponse)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return earthquakes
    }
}
