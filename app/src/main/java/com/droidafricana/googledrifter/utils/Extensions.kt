package com.droidafricana.googledrifter.utils

import android.app.Activity
import android.app.AlertDialog
import android.widget.Toast
import permissions.dispatcher.PermissionRequest

fun Activity.showDialog(title: String, message: String, request: PermissionRequest) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok) { dialog, _ ->
            request.proceed()
            dialog.dismiss()
        }
        .setNegativeButton(android.R.string.cancel) { dialog, _ ->
            request.cancel()
            dialog.dismiss()
        }
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show()
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
