package com.wk.learn.controller;

public interface IMusicController {
    void setMusicData(String path);
    long getCurrentPosition();
    void play();
    void pausePlay();
    void continuePlay();
    void seekTo(int process);
    boolean isPlaying();
    int getPosition();
    void stop();
}
