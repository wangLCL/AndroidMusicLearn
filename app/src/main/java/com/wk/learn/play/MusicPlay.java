package com.wk.learn.play;

import com.wk.learn.bean.MusicInfoBean;
import com.wk.learn.controller.MusicController;

import java.util.ArrayList;

public class MusicPlay {
    /**
     * 播放顺序
     * 1.播放列表中，播放完成了就删除
     * 2.按照歌曲的顺序进行播放
     *
     * 控制播放  比如更新进度条等
     */
    private static int playIndex;
    private static ArrayList<MusicInfoBean> playList;
    private static ArrayList<MusicInfoBean> songList;
    private static MusicController musicController;
    private static MusicInfoBean currentPlayInfo;
    private static Runnable quickPlayRunnable;

    public static void setPlayList(ArrayList<MusicInfoBean> playList) {
        MusicPlay.playList = playList;
    }

    public static void setSongList(ArrayList<MusicInfoBean> songList) {
        MusicPlay.songList = songList;
    }

    public static void setMusicController(MusicController _musicController) {
        musicController = _musicController;
    }

    public static void setMusicData(int _position){
        checkPlayService();
        MusicInfoBean musicInfoBean = songList.get(playIndex);
        if (currentPlayInfo!=null) {
            if (currentPlayInfo == musicInfoBean) {
                return;
            }
        }
        playIndex = _position;
        currentPlayInfo = songList.get(playIndex);
        musicController.setMusicData(currentPlayInfo.getPath());
    }

    public static void play(){
        checkPlayService();
        quickPlayRunnable.run();
        musicController.play();
    }

    private static boolean checkPlayService(){
        if (musicController == null){
            setMusicController(MusicController.getMusicController());
        }
        if (musicController == null){
            return false;
        }else {
            return true;
        }
    }

    public static MusicInfoBean getMusicInfo() {
        return currentPlayInfo;
    }

    public static void setQuickPlayRunnable(Runnable quickPlay) {
        quickPlayRunnable = quickPlay;
    }

    public static void pause() {
        musicController.pausePlay();
    }
}
