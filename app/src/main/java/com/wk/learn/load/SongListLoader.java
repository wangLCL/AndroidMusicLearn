package com.wk.learn.load;

import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.MusicInfoBean;

import java.util.ArrayList;

public class SongListLoader {
    public static ArrayList<MusicInfoBean> loadAllMusic(){
        ArrayList<MusicInfoBean> musicInfoBeans = new ArrayList<>();
        final StringBuilder selection = new StringBuilder();
        selection.append(MediaStore.Audio.AudioColumns.IS_MUSIC + "=1");
        selection.append(" AND " + MediaStore.Audio.AudioColumns.TITLE + " != ''");
        selection.append(" AND " + MediaStore.Audio.Media.DATE_ADDED + ">");
        Cursor mCursor = BaseApplication.context().getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, null,
                        null, MediaStore.Audio.AudioColumns.IS_MUSIC);


        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                long id = mCursor.getLong(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                String title = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                String artist = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                String album = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                int duration = mCursor.getInt(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                int trackNumber = mCursor.getInt(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TRACK));
                long artistId = mCursor.getInt(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID));
                long albumId = mCursor.getLong(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                String path = mCursor.getString(mCursor.getColumnIndexOrThrow((MediaStore.Audio.Media.DATA)));
                musicInfoBeans.add(new MusicInfoBean(id, albumId, artistId, title, artist, album, duration, trackNumber,path));
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return musicInfoBeans;
    }
}
