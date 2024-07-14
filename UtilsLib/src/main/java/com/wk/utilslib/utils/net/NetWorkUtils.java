package com.wk.utilslib.utils.net;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import androidx.annotation.RequiresPermission;

import com.wk.utilslib.utils.Utils;
import com.wk.utilslib.utils.thread.ThreadUtils;

import java.security.Permission;
import java.util.HashSet;
import java.util.Set;

public class NetWorkUtils {
    private NetWorkUtils(){
        throw new UnsupportedOperationException("");
    }

    /**
     * 打开setting
     */
    public static void openWirelessSetting(){
        Utils.getsApp().startActivity(
                new Intent(Settings.ACTION_WIFI_SETTINGS)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        );
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isConnected(){
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager cm =
                (ConnectivityManager) Utils
                        .getsApp()
                        .getSystemService(
                                Context.CONNECTIVITY_SERVICE
                        );
        if (cm == null)return null;
        return cm.getActiveNetworkInfo();
    }

    public static final class NetworkChangeReceiver extends BroadcastReceiver{
        private NetworkType mType;
        private Set<OnNetworkStatusChangedListener> mListeners = new HashSet<>();

        private static NetworkChangeReceiver getInstance(){
            return LazyHolder.INSTANCE;
        }

        private void registerListener(final OnNetworkStatusChangedListener listener){
            if (listener == null)return;
            ThreadUtils.runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    int size = mListeners.size();
                    mListeners.add(listener);
                    if (size == 0 && mListeners.size() == 1){
                        mType = getNetworkType();
                    }
                }

                private NetworkType getNetworkType() {
                    return null;
                }
            });
        }

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
    private static class LazyHolder{
        private static final NetworkChangeReceiver INSTANCE = new NetworkChangeReceiver();
    }

    public interface OnNetworkStatusChangedListener{
        void onDisconnected();
        void onConnected(NetworkType networkType);
    }
}
