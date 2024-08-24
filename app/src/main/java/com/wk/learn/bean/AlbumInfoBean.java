package com.wk.learn.bean;

public class AlbumInfoBean {
//    _id：专辑的唯一标识符。
    private final long id;
//    album：专辑名称。
    private final String album;
    //    artist_id：艺术家的唯一标识符。
    private final long artistId;
//    artist：艺术家名称。
    private final String artist;
//    numsongs：专辑中的歌曲数量。
    private final int numsongs;
//    minyear：专辑的最早发行年份。
    private final int minyear;
    public AlbumInfoBean(long id,String album,long artistId,String artist,int numsongs,int minyear){
        this.id = id;
        this.album = album;
        this.artistId = artistId;
        this.artist = artist;
        this.numsongs = numsongs;
        this.minyear = minyear;
    }

    public long getId() {
        return id;
    }

    public String getAlbum() {
        return album;
    }

    public long getArtistId() {
        return artistId;
    }

    public String getArtist() {
        return artist;
    }

    public int getNumsongs() {
        return numsongs;
    }

    public int getMinyear() {
        return minyear;
    }

    @Override
    public String toString() {
        return "AlbumInfoBean{" +
                "id=" + id +
                ", album='" + album + '\'' +
                ", artistId=" + artistId +
                ", artist='" + artist + '\'' +
                ", numsongs=" + numsongs +
                ", minyear=" + minyear +
                '}';
    }
}
