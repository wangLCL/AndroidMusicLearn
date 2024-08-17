package com.wk.learn.listener;

import android.media.AudioManager;

import com.wk.learn.controller.MusicController;

public class MusicFocusChangeListener implements AudioManager.OnAudioFocusChangeListener {
    private MusicController mediaPlayer;
    public MusicFocusChangeListener(MusicController mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.lossAudioFocus(1);
                }
                mediaPlayer.pausePlay();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                break;
            case AudioManager.AUDIOFOCUS_GAIN:
                if (!mediaPlayer.isPlaying()
                        && mediaPlayer.getLossFocuStatus()==1) {
                    mediaPlayer.lossAudioFocus(0);
                    mediaPlayer.play();
                }
                break;
        }
    }
}
