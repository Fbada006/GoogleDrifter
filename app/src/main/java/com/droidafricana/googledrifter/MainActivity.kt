package com.droidafricana.googledrifter

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidafricana.googledrifter.databinding.ActivityMainBinding
import com.droidafricana.googledrifter.utils.showDialog
import com.droidafricana.googledrifter.utils.toast
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getLocationWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun getLocation() {
        // Do some stuff here
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
}
