package com.wk.learn.load;

import android.database.Cursor;
import android.provider.MediaStore;

import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.ArtistInfoBean;

import java.util.ArrayList;

public class ArtistListLoader {
    /**
     * _id: 艺术家的唯一标识符。
     * artist: 艺术家名称。
     * number_of_albums: 艺术家的专辑数量。
     * number_of_tracks: 艺术家的歌曲数量。
     */
    public static ArrayList<ArtistInfoBean> loadArtistAll(){
        ArrayList<ArtistInfoBean> artistInfoBeans = new ArrayList<>();
        Cursor cursor = BaseApplication.context().getContentResolver().query(
                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                //id  name
                new String[]{"_id", "artist", "number_of_albums", "number_of_tracks"},
                null,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()) {
                do{
                    artistInfoBeans.add(
                            new ArtistInfoBean(
                                    cursor.getLong(0),
                                    cursor.getString(1),
                                    cursor.getInt(2),
                                    cursor.getInt(3)));
                }while (cursor.moveToNext());
            }
        }
        return artistInfoBeans;
    }
}
