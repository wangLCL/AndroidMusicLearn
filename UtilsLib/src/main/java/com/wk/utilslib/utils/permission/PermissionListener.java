package com.wk.utilslib.utils.permission;

public interface PermissionListener {
    void permissionChanged(String permissionChanged);
    void permissionGranted(String permissionGranted);
    void permissionRemoved(String permissionRemoved);
}
