package com.wk.ateutils;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.annotation.AttrRes;

public class Util {
    /**
     * 从theme中得到颜色值
     * @param context
     * @param attr
     * @return
     */
    public static int resolveColor(Context context, @AttrRes int attr) {
        return resolveColor(context, attr, 0);
    }

    //从主题中获取值
    public static int resolveColor(Context context, @AttrRes int attr, int fallback) {
        //获取指定属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            //获取颜色值，如果没有就返回备用值
            return a.getColor(0, fallback);
        } finally {
            //需要回收掉
            a.recycle();
        }
    }

    private Util() {
    }
}
