package com.wk.learn.bean;

public class ArtistInfoBean {
//    _id: 艺术家的唯一标识符。
//    artist: 艺术家名称。
//    number_of_albums: 艺术家的专辑数量。
//    number_of_tracks: 艺术家的歌曲数量。
    private final long _id;
    private final String artist;
    private final int numberOfAlbums;
    private final int numberOfTracks;
    public ArtistInfoBean(long _id, String artist, int numberOfAlbums, int numberOfTracks){
        this._id = _id;
        this.artist = artist;
        this.numberOfAlbums = numberOfAlbums;
        this.numberOfTracks = numberOfTracks;
    }

    public long get_id() {
        return _id;
    }

    public String getArtist() {
        return artist;
    }

    public int getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }
}
