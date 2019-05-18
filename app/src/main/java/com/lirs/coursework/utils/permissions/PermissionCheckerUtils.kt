package com.lirs.coursework.utils.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat

class PermissionCheckerUtils {

    private fun isPermissionGranted(
        context: Context,
        permission: RuntimePermissions
    ): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
            ActivityCompat
                .checkSelfPermission(context, permission.toStringValue()) ==
                    PackageManager.PERMISSION_GRANTED

    fun checkForPermissions(
        activity: Activity,
        permission: RuntimePermissions,
        callback: PermissionCallback
    ) {
        if (!isPermissionGranted(activity.applicationContext, permission)) {
            if (ActivityCompat
                    .shouldShowRequestPermissionRationale(
                        activity,
                        permission.toStringValue())) {
                // Здесь показываем обоснование, почему необходимо разрешение
                callback.permissionDenied(permission)
            } else {
                // Запрос диалога пермишна
                ActivityCompat
                    .requestPermissions(
                        activity,
                        arrayOf(permission.toStringValue()),
                        permission.ordinal
                    )
            }
        } else {
            // Права даны или АПИ < 23
            callback.permissionGranted(permission)
        }
    }

    enum class RuntimePermissions {

        PERMISSION_REQUEST_ACCESS_WIFI {

            override fun toStringValue(): String =
                Manifest.permission.ACCESS_WIFI_STATE

            override fun showInformationMessage(): String = ""
        };

        val VALUE: Int = this.ordinal

        abstract fun toStringValue(): String
        abstract fun showInformationMessage(): String
    }
}
