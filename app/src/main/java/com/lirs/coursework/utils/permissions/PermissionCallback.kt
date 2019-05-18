package com.lirs.coursework.utils.permissions

interface PermissionCallback {
    fun permissionGranted(permission: PermissionCheckerUtils.RuntimePermissions)
    fun permissionDenied(permission: PermissionCheckerUtils.RuntimePermissions)
}
