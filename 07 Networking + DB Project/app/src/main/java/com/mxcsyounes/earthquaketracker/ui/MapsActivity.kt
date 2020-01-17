package com.mxcsyounes.earthquaketracker.ui


import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.mxcsyounes.earthquaketracker.R
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        myLocationFab.hide()

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

    /*private fun showMarker(key: String, currentLocation: LatLng) {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        if (!currentLocationMarkers.containsKey(key)) {
            currentLocationMarkers[key] =
                map.addMarker(
                    MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker())
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(markersColors.shuffled()[0]))
                )
            currentLocationMarkers[key]?.tag = key
        } else
            MarkerAnimation.animateMarkerToGB(
                currentLocationMarkers[key]!!,
                latLng,
                LatLngInterpolator.Spherical()
            )
    }*/

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
