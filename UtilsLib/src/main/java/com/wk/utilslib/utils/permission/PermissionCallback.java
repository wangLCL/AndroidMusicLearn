package com.wk.utilslib.utils.permission;

public interface PermissionCallback {
    //已经授予
    void permissionGranted();
    //拒绝
    void permissionRefused();
}
