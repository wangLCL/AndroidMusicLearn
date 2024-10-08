package com.wk.learn.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import androidx.core.app.NotificationCompat;
import androidx.media.session.MediaButtonReceiver;

import com.wk.learn.R;
import com.wk.learn.bean.MusicInfoBean;
import com.wk.learn.listener.PlayStatusChangeListener;
import com.wk.learn.play.MusicPlay;
import com.wk.learn.service.MusicService;

import java.util.ArrayList;

public class SessionUtils {
    private MediaSessionCompat mSession;
    private PlaybackStateCompat mPlaybackState;
    private Activity mContext;
    private NotificationManager notificationManager;

    public static MediaControllerCompat controllerCompat;
    private static ArrayList<PlayStatusChangeListener> playStatusChangeListeners;

    private MediaSessionCompat.Callback mediasessionBack = new MediaSessionCompat.Callback() {
        @Override
        public void onPause() {
            musicPause();
        }

        @Override
        public void onPlay() {
            musicPlay();
        }

        @Override
        public void onSeekTo(long pos) {
            MusicPlay.seekTo((int) pos);
        }

        @Override
        public void onSkipToNext() {
            musicPlayNext();
        }

        @Override
        public void onSkipToPrevious() {
            musicPlayPre();
        }

        @Override
        public void onStop() {
            MusicPlay.stop();
        }
    };

    public void musicPlayPre() {
        MusicPlay.playPre();
        mPlaybackState = new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                .build();
        mSession.setPlaybackState(mPlaybackState);
    }

    private void musicPlayNext() {
        MusicPlay.playNext();
        mPlaybackState = new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_SKIPPING_TO_NEXT, 0, 1.0f)
                .build();
        mSession.setPlaybackState(mPlaybackState);
    }

    public void musicPlay() {
        mPlaybackState = new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                .build();
        mSession.setPlaybackState(mPlaybackState);
        MusicPlay.continuePlay();
        updateNotification();
        for (PlayStatusChangeListener playStatusChangeListener : playStatusChangeListeners) {
            playStatusChangeListener.updateBtnStatus();
        }
    }

    public void musicPause() {
        MusicPlay.pause();
        mPlaybackState = new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_PAUSED, 0, 1.0f)
                .build();
        mSession.setPlaybackState(mPlaybackState);
        updateNotification();
        for (PlayStatusChangeListener playStatusChangeListener : playStatusChangeListeners) {
            playStatusChangeListener.updateBtnStatus();
        }
    }

    public SessionUtils(Activity activity){
        this.mContext = activity;
        mSession = new MediaSessionCompat(activity, "Music");
        mSession.setFlags(MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
                | MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS);
        mSession.setCallback(mediasessionBack);
        mSession.setActive(true);
        notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        controllerCompat = new MediaControllerCompat(activity,getSessionToken());
//        controllerCompat.registerCallback(mMediaControllerCallback);
        this.playStatusChangeListeners = new ArrayList<>();
    }

    public static void addPlayStatusChangeListener(PlayStatusChangeListener listener){
        playStatusChangeListeners.add(listener);
    }

    public static void removePlayStatusChangeListener(PlayStatusChangeListener listener){
        playStatusChangeListeners.remove(listener);
    }

    public MediaSessionCompat.Token getSessionToken() {
        return mSession.getSessionToken();
    }

    /**
     * 更新通知栏
     */
    public void updateNotification() {
        MusicInfoBean musicInfo = MusicPlay.getMusicInfo();
        if (Build.VERSION.SDK_INT<26) {
            NotificationCompat.Action playPauseAction = mPlaybackState.getState() ==
                    PlaybackStateCompat.STATE_PLAYING ?
                    createAction(R.mipmap.ic_pause_white_36dp, "Pause", MusicService.ACTION_PAUSE) :
                    createAction(R.mipmap.ic_play_white_36dp, "Play", MusicService.ACTION_PLAY);
            Drawable drawable = mContext.getApplicationInfo().loadIcon(mContext.getPackageManager());
            Bitmap bitmap = getBitmapFromDrawable(drawable);
            NotificationCompat.Builder notificationCompat
                    = new NotificationCompat.Builder(mContext)
                    .setContentTitle(musicInfo.getTitle())
                    .setContentText(musicInfo.getArtistName())
                    .setLargeIcon(bitmap)
//        设置这是否是一个正在进行的通知。用户无法拒绝正在进行的通知，因此您的应用程序或服务必须负责取消这些通知。它们通常用于指示
//        用户正在积极参与（例如，播放音乐）或以某种方式挂起并因此占用设备的后台任务（例如，文件下载、同步操作、活动网络连接）。
                    .setOngoing(mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING)
                    .setShowWhen(false)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(false)
                    .addAction(createAction(R.mipmap.ic_skip_previous_white_36dp, "last", MusicService.ACTION_LAST))
                    .addAction(playPauseAction)
                    .addAction(createAction(R.mipmap.ic_skip_next_white_36dp, "next", MusicService.ACTION_NEXT))
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setMediaSession(mSession.getSessionToken())
                            .setShowActionsInCompactView(0, 1, 2));
            Notification notification = notificationCompat.build();
            ((NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, notification);
        }else {
            NotificationCompat.Action playPauseAction = mPlaybackState.getState() ==
                    PlaybackStateCompat.STATE_PLAYING ?
                    createAction(R.mipmap.ic_pause_white_36dp, "Pause", MusicService.ACTION_PAUSE) :
                    createAction(R.mipmap.ic_play_white_36dp, "Play", MusicService.ACTION_PLAY);

            NotificationChannel mChannel = new NotificationChannel("channelID", "channelName",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(mChannel);
            Drawable drawable = mContext.getApplicationInfo().loadIcon(mContext.getPackageManager());
            Bitmap bitmap = getBitmapFromDrawable(drawable);
            NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(mContext,"channelID")
                    .setContentTitle(musicInfo.getTitle())
                    .setContentText(musicInfo.getArtistName())
                    .setLargeIcon(bitmap)
//        设置这是否是一个正在进行的通知。用户无法拒绝正在进行的通知，因此您的应用程序或服务必须负责取消这些通知。它们通常用于指示
//        用户正在积极参与（例如，播放音乐）或以某种方式挂起并因此占用设备的后台任务（例如，文件下载、同步操作、活动网络连接）。
                    .setOngoing(mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING)
                    .setShowWhen(false)
                    .setSmallIcon(R.mipmap.ic_pause_white_36dp)
                    .setAutoCancel(false)
                    .addAction(createAction(R.mipmap.ic_skip_previous_white_36dp, "last", MusicService.ACTION_LAST))
                    .addAction(playPauseAction)
                    .addAction(createAction(R.mipmap.ic_skip_next_white_36dp, "next", MusicService.ACTION_NEXT))
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setMediaSession(mSession.getSessionToken())
                            .setShowActionsInCompactView(0, 1, 2));
//            ((NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, notification);
            notificationManager.notify(1, notificationCompat.build());
        }
        //播放新歌曲时需要更新seekbar的max与总秒数对应。保证每一秒seekbar会走动一格。回传到View层来更新时间
//        this.seekBar.setMax(mMediaPlayer.getDuration() / 1000);
//        updateSeekBar();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

    private NotificationCompat.Action createAction(int iconResId, String title, String action) {
        Intent intent = new Intent(mContext, MusicService.class);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 1, intent,PendingIntent.FLAG_IMMUTABLE);
        return new NotificationCompat.Action(iconResId, title, pendingIntent);
    }
}
