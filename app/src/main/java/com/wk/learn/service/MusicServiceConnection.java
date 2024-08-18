package com.wk.learn.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.wk.learn.controller.MusicController;

public class MusicServiceConnection implements ServiceConnection {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicController.setMusicController((MusicController) service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        MusicController.setMusicController(null);
    }
}
