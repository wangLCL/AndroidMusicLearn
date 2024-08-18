package com.wk.learn.controller;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;

import com.wk.learn.application.BaseApplication;
import com.wk.learn.listener.MusicFocusChangeListener;

import java.io.IOException;

/**
 * 播放工具类
 *
 * 单纯做播放
 */
public class MusicController extends Binder implements IMusicController{
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private int mediaStatus;
    private MusicFocusChangeListener focusChangeListener;
    public MusicController(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.mAudioManager = (AudioManager) BaseApplication.context().getSystemService(Context.AUDIO_SERVICE);
        this.focusChangeListener = new MusicFocusChangeListener(this);
    }

    private static MusicController musicController;

    public static void setMusicController(MusicController _musicController) {
        musicController = _musicController;
    }

    public static MusicController getMusicController(){
        if (musicController == null){
            throw new RuntimeException("service start error!");
        }
        return musicController;
    }

    @Override
    public void setMusicData(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public long getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void play() {
        continuePlay();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
    }

    @Override
    public void pausePlay() {
        mediaPlayer.pause();
        mAudioManager.abandonAudioFocus(null);
    }

    @Override
    public void continuePlay() {
        //获取焦点
        int result = mAudioManager.requestAudioFocus(
                focusChangeListener, // 指定焦点变化的回调，可以为null
                AudioManager.STREAM_MUSIC, // 指定音频流的类型
                AudioManager.AUDIOFOCUS_GAIN // 指定请求焦点类型
        );
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // 成功获得焦点，可以播放音频
//         mAudioManager.requestAudioFocus(, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            mediaPlayer.start();           //继续播放音乐
        } else {
            // 未获得焦点，需要停止播放音频
            mAudioManager.abandonAudioFocus(null); // 指定焦点变化的回调，可以为null
        }
    }

    @Override
    public void seekTo(int process) {
        mediaPlayer.seekTo(process);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        mAudioManager.abandonAudioFocus(null);
    }

    public void lossAudioFocus(int i) {
        this.mediaStatus = i;
    }

    public int getLossFocuStatus() {
        return mediaStatus;
    }
}
