package com.wk.learn.load;

import android.database.Cursor;
import android.provider.MediaStore;

import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.AlbumInfoBean;

import java.util.ArrayList;
import java.util.List;

public class AlbumListLoader {
    public static List<AlbumInfoBean> loadAllAlbumList(){
        List<AlbumInfoBean> albumInfoBeans = new ArrayList<>();
        Cursor cursor = BaseApplication.context().getContentResolver()
                .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                        new String[]{"_id", "album", "artist_id","artist", "numsongs", "minyear"},
                        null, null, null);
        if (cursor!=null){
            if (cursor.moveToFirst()) {
                do{
                    albumInfoBeans.add(
                            new AlbumInfoBean(
                                    cursor.getLong(0),
                                    cursor.getString(1),
                                    cursor.getLong(2),
                                    cursor.getString(3),
                                    cursor.getInt(4),
                                    cursor.getInt(5)));
                }while (cursor.moveToNext());
            }
        }
        return albumInfoBeans;
    }
}
