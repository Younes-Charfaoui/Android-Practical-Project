package com.mxcsyounes.earthquaketracker.ui.mapActivity


import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
import com.mxcsyounes.earthquaketracker.dataLayers.NetworkLayer
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapsViewModel: MapsViewModel
    private val currentLocationMarkers = mutableMapOf<String, Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)

        val apiKey = "AIzaSyBMniVmj6XeovDTMA0Rkh6j1br6T4TwWxA"


        if (!Places.isInitialized()) {
            Places.initialize(this, apiKey)
        }

        //val placesClient = Places.createClient(this)

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
                    CoroutineScope(Dispatchers.IO).launch {
                        val result =
                            NetworkLayer().searchByLatLang(latLng.latitude, latLng.longitude)
                        Log.d("Leme", result.earthquakes.toString())
                        withContext(Dispatchers.Main) {
                            for (earthquake in result.earthquakes) {
                                with(earthquake) {
                                    showMarker(eqid, LatLng(lat, lng))
                                }
                            }
                        }
                    }
                }
            }

            override fun onError(status: Status) {
                Log.i("Leme", "An error occurred: $status")
            }
        })

        myLocationFab.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this.applicationContext,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnCompleteListener {
                    val location = it.result
                    if (location != null)
                        map.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    location.latitude,
                                    location.longitude
                                ), 15.0f
                            )
                        )
                }
            }
        }
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        myLocationFab.show()
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
            map.uiSettings.isMyLocationButtonEnabled = false
            fusedLocationClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null)
                    map.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                location.latitude,
                                location.longitude
                            ), 15.0f
                        )
                    )
            }
        } else {
            Toast.makeText(this, "Please accept permission", Toast.LENGTH_LONG).show()
        }
    }

    private fun showMarker(key: String, currentLocation: LatLng) {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        if (!currentLocationMarkers.containsKey(key)) {
            currentLocationMarkers[key] =
                map.addMarker(
                    MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker())
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker())
                )
            currentLocationMarkers[key]?.tag = key
        }
    }

    fun animateCamera(location: LatLng) {
        val latLng = LatLng(location.latitude, location.longitude)
        map.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)))
    }

    private fun getCameraPositionWithBearing(latLng: LatLng): CameraPosition {
        return CameraPosition.Builder().target(latLng).zoom(15.0f).build()
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
