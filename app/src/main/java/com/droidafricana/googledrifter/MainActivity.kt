package com.droidafricana.googledrifter

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.droidafricana.googledrifter.databinding.ActivityMainBinding
import com.droidafricana.googledrifter.utils.Constants
import com.droidafricana.googledrifter.utils.showDialog
import com.droidafricana.googledrifter.utils.toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding

    private lateinit var map: GoogleMap

    private val viewModel: CurrentLocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun enableLocation() {
        map.isMyLocationEnabled = true
    }

    @OnShowRationale(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun showRationaleForLocation(request: PermissionRequest) {
        showDialog(
            getString(R.string.location_permission_title),
            getString(R.string.location_permission_rationale),
            request
        )
    }

    @OnPermissionDenied(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun onLocationDenied() {
        toast(getString(R.string.location_permission_denied_message))
    }

    @OnNeverAskAgain(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun onLocationNeverAskAgain() {
        toast(getString(R.string.location_permission_never_ask_message))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE

        enableLocationWithPermissionCheck()

        viewModel.locationData.observe(this, Observer {
            val latLng = LatLng(it.latitude, it.longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM))
            map.addMarker(MarkerOptions().position(latLng))
        })

        viewModel.setMapLongClick(map)
        viewModel.setPoiClick(map)
    }
}
