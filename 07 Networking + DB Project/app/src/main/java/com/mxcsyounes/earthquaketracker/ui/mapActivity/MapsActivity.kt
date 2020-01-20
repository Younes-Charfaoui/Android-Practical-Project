package com.mxcsyounes.earthquaketracker.ui.mapActivity


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.mxcsyounes.earthquaketracker.R
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake
import com.mxcsyounes.earthquaketracker.ui.detailAcitivity.DetailActivity
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapsViewModel: MapsViewModel
    private val currentLocationMarkers = mutableMapOf<String, Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        requestPermission()

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)

        val apiKey = getString(R.string.api_key_places)


        if (!Places.isInitialized()) {
            Places.initialize(this, apiKey)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        myLocationFab.hide()

        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.LAT_LNG,
                Place.Field.NAME,
                Place.Field.ADDRESS
            )
        )

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i(
                    "Leme",
                    "Place: " + place.name + ", " + place.id + ", " + place.latLng?.latitude + ", " + place.latLng?.longitude
                )
                val latLng = place.latLng
                if (latLng != null) {
                    animateCamera(latLng)
                    mapsViewModel.getEarthquakeFromLatLong(latLng, this@MapsActivity)
                        .observe(this@MapsActivity,
                            Observer<List<Earthquake>> {
                                removeMarkers()
                                for (earthquake in it) {
                                    with(earthquake) {
                                        showMarker(eqid, LatLng(lat, lng), magnitude, depth)
                                    }
                                }
                            })
                }
            }

            override fun onError(status: Status) {
                Log.i("Leme", "An error occurred: $status")
            }
        })

        myLocationFab.setOnClickListener {
            if (checkPermission()) {
                getLastLocationOnMap()
            }
        }

        detailButton.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
        mapFragment.getMapAsync(this)
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this.applicationContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getLastLocationOnMap() {
        fusedLocationClient.lastLocation.addOnCompleteListener {
            val location = it.result
            if (location != null)
                animateCamera(LatLng(location.latitude, location.longitude))
        }
    }

    private fun requestPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                55
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        myLocationFab.show()
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
            map.uiSettings.isMyLocationButtonEnabled = false
            getLastLocationOnMap()
        } else {
            Toast.makeText(this, "Please accept permission", Toast.LENGTH_LONG).show()
        }
    }

    private fun showMarker(
        key: String,
        currentLocation: LatLng,
        magnitude: Double,
        depth: Double
    ) {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        if (!currentLocationMarkers.containsKey(key)) {
            currentLocationMarkers[key] =
                map.addMarker(
                    MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker())
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker())
                        .snippet("Depth of $depth km")
                        .title("Magnitude of $magnitude")
                )
            currentLocationMarkers[key]?.tag = key
        }
    }

    private fun removeMarkers() {
        for (marker in currentLocationMarkers.values) {
            marker.remove()
        }
    }

    fun animateCamera(location: LatLng) {
        val latLng = LatLng(location.latitude, location.longitude)
        map.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)))
    }

    private fun getCameraPositionWithBearing(latLng: LatLng): CameraPosition {
        return CameraPosition.Builder().target(latLng).zoom(10.0f).build()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = MapsActivity::class.java.canonicalName
    }
}
