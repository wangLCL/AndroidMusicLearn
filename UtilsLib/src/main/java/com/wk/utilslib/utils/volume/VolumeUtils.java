package com.wk.utilslib.utils.volume;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import com.wk.utilslib.utils.Utils;

public class VolumeUtils {

    public static int getVolume(int streamType) {
        AudioManager am = (AudioManager) Utils.getsApp().getSystemService(Context.AUDIO_SERVICE);
        return am.getStreamVolume(streamType);
    }

    public static void setVolume(int streamType,
                                 int volume,
                                 int flags){
        AudioManager am = (AudioManager) Utils.getsApp().getSystemService(Context.AUDIO_SERVICE);
        try {
            am.setStreamVolume(streamType,volume,flags);
        }catch (SecurityException ignore){
            ignore.printStackTrace();
        }
    }

    public static int getMaxVolume(int streamType){
        AudioManager am = (AudioManager) Utils.getsApp().getSystemService(Context.AUDIO_SERVICE);
        return am.getStreamMaxVolume(streamType);
    }

    public static int getMinVolume(int streamType){
        AudioManager am = (AudioManager) Utils.getsApp().getSystemService(Context.AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            return am.getStreamMinVolume(streamType);
        }
        return 0;
    }
}
