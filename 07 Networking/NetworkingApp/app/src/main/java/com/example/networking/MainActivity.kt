package com.example.networking

import android.app.LoaderManager
import android.content.Context
import android.content.Intent
import android.content.Loader
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import java.util.ArrayList

class MainActivity : AppCompatActivity(),
    LoaderManager.LoaderCallbacks<ArrayList<Earthquake>> {
    lateinit var adapter: CustomEarthquakeAdapter
    internal var earthquakes: ArrayList<Earthquake>? = null
    lateinit var earthquakeListView: ListView
    lateinit var listTv: TextView
    lateinit var listProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        earthquakeListView = findViewById<View>(R.id.list) as ListView
        listTv = findViewById<View>(R.id.listTv) as TextView
        listProgress = findViewById<View>(R.id.listProgress) as ProgressBar

        earthquakeListView.emptyView = listTv

        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnected) {
            listTv.setText(R.string.no_connection)
            listProgress.visibility = View.GONE
        } else {
            loaderManager.initLoader(0, null, this)
        }
    }

    // Creating new Loader
    override fun onCreateLoader(i: Int, bundle: Bundle): Loader<ArrayList<Earthquake>> {

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val minMagnitude = sharedPrefs.getString(
            getString(R.string.settings_min_magnitude_key),
            getString(R.string.settings_min_magnitude_default)
        )
        val orderBy = sharedPrefs.getString(
            getString(R.string.settings_order_by_key),
            getString(R.string.settings_order_by_default)
        )
        val baseUri = Uri.parse(CONNECT_URL)
        val uriBuilder = baseUri.buildUpon()

        uriBuilder.appendQueryParameter("format", "geojson")
        uriBuilder.appendQueryParameter("limit", "10")
        uriBuilder.appendQueryParameter("minmag", minMagnitude)
        uriBuilder.appendQueryParameter("orderby", orderBy)
        return EarthquakeLoader(this, uriBuilder.toString(), earthquakes)
    }

    // Function handling situation after loading Loader
    override fun onLoadFinished(
        loader: Loader<ArrayList<Earthquake>>,
        earthquakes: ArrayList<Earthquake>
    ) {
        adapter = CustomEarthquakeAdapter(this@MainActivity, earthquakes)
        earthquakeListView.adapter = adapter
        listTv.setText(R.string.no_earthquakes_found)
        listProgress.visibility = View.GONE
    }

    // Function used when the loader is being reset
    override fun onLoaderReset(loader: Loader<ArrayList<Earthquake>>) {
        adapter.clear()
    }

    // Menu options
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val CONNECT_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query"
    }
}
