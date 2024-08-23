package com.wk.learn.load;

import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.MusicInfoBean;

import java.util.ArrayList;

public class SongListLoader {
    /**
     * ContentResolver contentResolver = getContentResolver();
     *
     * // 指定要查询的音乐数据的URI
     * Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
     *
     * // 指定要查询的字段
     * String[] projection = {
     *     MediaStore.Audio.Media._ID,
     *     MediaStore.Audio.Media.TITLE,
     *     MediaStore.Audio.Media.ARTIST,
     *     MediaStore.Audio.Media.DURATION
     * };
     *
     * // 执行查询
     * Cursor cursor = contentResolver.query(
     *     uri,                   // 要查询的URI
     *     projection,            // 要返回的列
     *     null,                  // 查询条件字符串
     *     null,                  // 查询条件中的参数
     *     MediaStore.Audio.Media.TITLE + " ASC"  // 排序
     * );
     * @return
     */
    public static ArrayList<MusicInfoBean> loadAllMusic(){
        ArrayList<MusicInfoBean> musicInfoBeans = new ArrayList<>();
        Cursor mCursor = BaseApplication.context().getContentResolver()
                .query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null,
                        null,
                        null,
                        MediaStore.Audio.AudioColumns.IS_MUSIC);
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
