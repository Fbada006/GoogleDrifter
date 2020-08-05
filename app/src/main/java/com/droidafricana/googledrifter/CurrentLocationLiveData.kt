package com.droidafricana.googledrifter

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.LiveData
import com.droidafricana.googledrifter.model.DrifterLocation
import com.droidafricana.googledrifter.utils.Constants
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class CurrentLocationLiveData(application: Application) : LiveData<DrifterLocation>() {

    private var fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = Constants.LOCATION_UPDATE_INTERVAL
        fastestInterval = Constants.LOCATION_UPDATE_FASTEST_INTERVAL
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location: Location) {
        value = DrifterLocation(
            longitude = location.longitude,
            latitude = location.latitude
        )
    }
}
