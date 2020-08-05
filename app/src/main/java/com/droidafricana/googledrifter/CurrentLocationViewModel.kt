package com.droidafricana.googledrifter

import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.Locale

class CurrentLocationViewModel(application: Application) :
    AndroidViewModel(application) {

    private val context = application.applicationContext

    private val _locationData = CurrentLocationLiveData(application)

    val locationData: CurrentLocationLiveData
        get() = _locationData

    fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            map.addMarker(
                MarkerOptions().position(latLng)
                    .title(getAddress(latLng.latitude, latLng.longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }

    fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            ).showInfoWindow()
        }
    }

    private fun getAddress(latitude: Double, longitude: Double): String? {
        return try {
            val myLocation = Geocoder(context, Locale.getDefault())
            val addressList = myLocation.getFromLocation(latitude, longitude, 1)
            val address = addressList[0]
            val addressStr = address.getAddressLine(0)
            addressStr
        } catch (e: IOException) {
            e.printStackTrace()
            context.getString(R.string.unknown_place)
        }
    }
}
