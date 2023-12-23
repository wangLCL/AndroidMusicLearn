package com.wk.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selfView(View view) {
        Intent intent = new Intent(this,SelfActivity.class);
        startActivity(intent);
    }

    public void drawColor(View view) {

        Intent intent = new Intent(this,DrawColorActivity.class);
        startActivity(intent);
    }

    public void optionCanvas(View view) {
        Intent intent = new Intent(this,CanvasOptionActivity.class);
        startActivity(intent);
    }
}