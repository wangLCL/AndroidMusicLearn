package com.wk.utilslib.utils.permission;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtils {
    //没有的权限存储起来  下落进行请求
    public static ArrayList<String> requestPermission(Activity activity,String[] permissions,int code){
        ArrayList<String> permissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (hasPermission(activity, permission)) {
                permissionsNeeded.add(permission);
            }
        }

        if (!permissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    permissionsNeeded.toArray(new String[0]),
                    code);
        }
        return permissionsNeeded;
    }

    //是否含义这个权限
    public static boolean hasPermission(Activity activity, String permission) {
        return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasPermission(Activity activity, String[] permissions) {
        for (String permission : permissions) {
            if (hasPermission(activity,permission)) {
                return false;
            }
        }
        return true;
    }
}
