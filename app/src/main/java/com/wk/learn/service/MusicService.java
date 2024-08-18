package com.wk.learn.service;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.IBinder;
import android.service.media.MediaBrowserService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wk.learn.DrawLayoutActivity;
import com.wk.learn.controller.MusicController;

import java.util.List;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    public static final String SESSION_TAG = "mMusic";
    public static final String ACTION_PLAY = "play";
    public static final String ACTION_PAUSE = "pause";
    public static final String ACTION_NEXT = "next";
    public static final String ACTION_LAST = "last";
    public static final String PARAM_TRACK_URI = "uri";
    public static final String PLAY_DATA = "data";

    @Override
    public void onCreate() {
        super.onCreate();
        this.mediaPlayer = new MediaPlayer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicController(mediaPlayer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null) {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }

    /**
     *
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    DrawLayoutActivity.controllerCompat.getTransportControls().play();
                    break;
                case ACTION_NEXT:
                    DrawLayoutActivity.controllerCompat.getTransportControls().skipToNext();
                    break;
                case ACTION_LAST:
                    DrawLayoutActivity.controllerCompat.getTransportControls().skipToPrevious();
                    break;
                case ACTION_PAUSE:
                    DrawLayoutActivity.controllerCompat.getTransportControls().pause();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

}
