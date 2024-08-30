package com.wk.utilslib.utils.time;

public class TimeUtils {
    public static StringBuilder timeResult = new StringBuilder();
    public static String minutesAndSecond(long time){
        timeResult.setLength(0);
        long miao = time / 1000;
        long fen = miao / 60;
        if (fen<10){
            timeResult.append("0");
        }
        timeResult.append(fen);
        timeResult.append(":");
        long l = miao % 60;
        if (l<10){
            timeResult.append("0");
        }
        timeResult.append(l);
        return timeResult.toString();
    }
}
