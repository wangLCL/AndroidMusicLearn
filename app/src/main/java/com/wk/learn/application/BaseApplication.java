package com.wk.learn.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.wk.learn.service.MusicService;
import com.wk.learn.service.MusicServiceConnection;

public class BaseApplication extends Application {

    private static Context context;
    private MusicServiceConnection connection;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Intent intent = new Intent(context(),MusicService.class);
        connection = new MusicServiceConnection();
        bindService(intent,connection,BIND_AUTO_CREATE);

    }

    public static Context context(){
        return context;
    }
}
