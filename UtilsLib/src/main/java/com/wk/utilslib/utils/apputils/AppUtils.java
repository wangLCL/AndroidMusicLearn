package com.wk.utilslib.utils.apputils;

import android.net.Uri;

import com.wk.utilslib.utils.Utils;
import com.wk.utilslib.utils.listener.OnAppStatusChangedListener;

import java.io.File;

public class AppUtils {
    /**
     * 前后台
     * @param listener
     */
    public static void registerAppStatusChangedListener(OnAppStatusChangedListener listener){

    }

    public static void unRegisterAppStatusChangeListener(OnAppStatusChangedListener listener){

    }

    public static void installApp(final String filePath){

    }

    public static void installApp(File file){

    }

    public static void installApp(Uri uri){

    }

    public static void uninstallApp(String packageName){

    }

    public static boolean isAppInstalled(String pkgName){
        return false;
    }

    public static boolean isAppRoot(){
//        ShellUtils.CommandResult result = UtilsBridge.execCmd("echo root", true);
        return false;
    }
}
