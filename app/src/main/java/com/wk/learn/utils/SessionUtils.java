package com.wk.learn.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import androidx.core.app.NotificationCompat;

import com.wk.learn.R;
import com.wk.learn.play.MusicPlay;
import com.wk.learn.service.MusicService;

public class SessionUtils {
    private MediaSessionCompat mSession;
    private PlaybackStateCompat mPlaybackState;

    private MediaSessionCompat.Callback mediasessionBack = new MediaSessionCompat.Callback() {
        @Override
        public void onPause() {
            MusicPlay.pause();
            mPlaybackState = new PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PAUSED, 0, 1.0f)
                    .build();
            mSession.setPlaybackState(mPlaybackState);
            updateNotification();
        }

        @Override
        public void onPlay() {
//            if (MusicPlay.isPlaying()) {
//                MusicPlay.pause();
//                mPlaybackState = new PlaybackStateCompat.Builder()
//                        .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
//                        .build();
//                mSession.setPlaybackState(mPlaybackState);
//
//            }else {

                mPlaybackState = new PlaybackStateCompat.Builder()
                        .setState(PlaybackStateCompat.STATE_PAUSED, 0, 1.0f)
                        .build();
                mSession.setPlaybackState(mPlaybackState);
                MusicPlay.continuePlay();
//            }

            updateNotification();
        }

        @Override
        public void onSeekTo(long pos) {
            MusicPlay.seekTo((int) pos);
        }

        @Override
        public void onSkipToNext() {
            MusicPlay.playNext();
        }

        @Override
        public void onSkipToPrevious() {
            MusicPlay.playPre();
        }

        @Override
        public void onStop() {
            MusicPlay.stop();
        }
    };

    private Activity mContext;
    public SessionUtils(Activity activity){
        this.mContext = activity;
        mSession = new MediaSessionCompat(activity, "Music");
        mSession.setFlags(MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
                | MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS);
        mSession.setCallback(mediasessionBack);
        mSession.setActive(true);
    }

    public void setMetadata(MediaMetadataCompat metadata) {
        mSession.setMetadata(metadata);
    }

    public MediaSessionCompat.Token getSessionToken() {
        return mSession.getSessionToken();
    }

    public MediaSessionCompat getmSession() {
        return mSession;
    }


    /**
     * 更新通知栏
     */
    private void updateNotification() {
        NotificationCompat.Action playPauseAction = mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING ?
                createAction(R.drawable.above_shadow, "Pause", MusicService.ACTION_PAUSE) :
                createAction(R.drawable.song_icon_bg, "Play", MusicService.ACTION_PLAY);

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(mContext)
                .setContentTitle("title")
                .setContentText("content")
                .setOngoing(mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING)
                .setShowWhen(false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false)
                .addAction(createAction(R.drawable.song_icon_bg, "last", MusicService.ACTION_LAST))
                .addAction(playPauseAction)
                .addAction(createAction(R.drawable.song_icon_bg, "next", MusicService.ACTION_NEXT))
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mSession.getSessionToken())
                        .setShowActionsInCompactView(1, 2));
        Notification notification = notificationCompat.build();
        ((NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, notification);


        //播放新歌曲时需要更新seekbar的max与总秒数对应。保证每一秒seekbar会走动一格。回传到View层来更新时间
//        this.seekBar.setMax(mMediaPlayer.getDuration() / 1000);

//        updateSeekBar();
    }


    private NotificationCompat.Action createAction(int iconResId, String title, String action) {
        Intent intent = new Intent(mContext, MusicService.class);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 1, intent,0);
        return new NotificationCompat.Action(iconResId, title, pendingIntent);
    }

}
