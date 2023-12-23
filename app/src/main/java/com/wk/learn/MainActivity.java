package com.wk.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private static final String WIFI_AP_ADDRESS = "192.168.43.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        checkNet();
//        getLocalInetAddress(); //192.168.0.102
//        getLocalInetAddress1();
    }



    private boolean isConnectedToWifi() {
        ConnectivityManager cm = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected()
                && ni.getType() == ConnectivityManager.TYPE_WIFI;
    }


    //判断网络
    public boolean isConnectedToLocalNetwork() {
        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        connected = ni != null
                && ni.isConnected()
                && (ni.getType() & (ConnectivityManager.TYPE_WIFI | ConnectivityManager.TYPE_ETHERNET)) != 0;
        if (!connected) {
//            Gdx.app.log("NET", "isConnectedToLocalNetwork: see if it is an USB AP");
            try {
                for (NetworkInterface netInterface : Collections.list(NetworkInterface
                        .getNetworkInterfaces())) {
                    if (netInterface.getDisplayName().startsWith("rndis")) {
                        connected = true;
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        return connected;
    }


    //是否启用热点
    public boolean isEnabledWifiHotspot() {
        boolean enabled = false;
//        Gdx.app.log("NET", "isEnabledWifiHotspot: see if it is an WIFI AP");
        WifiManager wm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        try {
            Method method = wm.getClass().getDeclaredMethod("isWifiApEnabled");
            enabled = (Boolean) method.invoke(wm);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return enabled;
    }

    public InetAddress getLocalInetAddress() {
        /**
         * 没网  没热点
         */
        if (!isConnectedToLocalNetwork() && !isEnabledWifiHotspot()) {
//            Gdx.app.log("NET", "getLocalInetAddress called and no connection");
            return null;
        }

        if (isConnectedToWifi()) {
            WifiManager wm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            int ipAddress = wm.getConnectionInfo().getIpAddress();
            if (ipAddress == 0)
                return null;
            return intToInet(ipAddress);
        }

        return null;
    }


    public InetAddress getLocalInetAddress1() {
        InetAddress savedAddress = null;

        try {
            Enumeration<NetworkInterface> netinterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (netinterfaces.hasMoreElements()) {
                NetworkInterface netinterface = netinterfaces.nextElement();
                Enumeration<InetAddress> addresses = netinterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();

                    if (isEnabledWifiHotspot()
                            && WIFI_AP_ADDRESS.equals(address.getHostAddress()))
                        return address;

                    // this is the condition that sometimes gives problems
                    if (!address.isLoopbackAddress()
                            && !address.isLinkLocalAddress()) {
                        if (address instanceof Inet4Address)
                            return address;
                        else
                            savedAddress = address;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedAddress;
    }
    public static byte byteOfInt(int value, int which) {
        int shift = which * 8;
        return (byte) (value >> shift);
    }

    public static InetAddress intToInet(int value) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = byteOfInt(value, i);
        }
        try {
            return InetAddress.getByAddress(bytes);
        } catch (UnknownHostException e) {
            // This only happens if the byte array has a bad length
            return null;
        }
    }

    public void checkNet(){
        boolean connected;
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        connected = ni != null && ni.isConnected()
                && (ni.getType() & (ConnectivityManager.TYPE_WIFI | ConnectivityManager.TYPE_ETHERNET)) != 0;
        if (!connected) {
//            Logger.log("NET", "isConnectedToLocalNetwork: see if it is an USB AP");
            try {
                for (NetworkInterface netInterface : Collections.list(NetworkInterface
                        .getNetworkInterfaces())) {
                    System.out.println(netInterface.getDisplayName());
                    if (netInterface.getDisplayName().startsWith("rndis")) {
                        connected = true;
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

    }
}