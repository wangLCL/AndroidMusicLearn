package com.wk.learn.play;

import com.wk.learn.bean.MusicInfoBean;

import java.util.ArrayList;

public class MusicPlay {
    /**
     * 播放顺序
     * 1.播放列表中，播放完成了就删除
     * 2.按照歌曲的顺序进行播放
     *
     * 控制播放  比如更新进度条等
     */
    private ArrayList<MusicInfoBean> playList;
    private ArrayList<MusicInfoBean> songList;
    public MusicPlay (ArrayList<MusicInfoBean> playList,ArrayList<MusicInfoBean> songList){
        this.playList = playList;
        this.songList = songList;
    }



}
