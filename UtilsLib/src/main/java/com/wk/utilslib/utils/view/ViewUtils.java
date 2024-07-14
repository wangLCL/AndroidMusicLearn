package com.wk.utilslib.utils.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wk.utilslib.utils.Utils;

public class ViewUtils {
//    布局是 是从左到右边 还是右边到左
    public static boolean isLayoutRtl(){
        return false;
    }

    public static void runUiThreadDelayed(
            final Runnable runnable,
            long delayMillis
    ){

    }

    /**
     * 这个不知道
     * @param view
     */
    public static void fixScrollViewTopping(View view){
        view.setFocusable(false);
        ViewGroup viewGroup = null;
        if (view instanceof ViewGroup){
            viewGroup = (ViewGroup) view;
        }
        if (viewGroup == null){
            return;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setFocusable(false);
            if (childAt instanceof ViewGroup){
                fixScrollViewTopping(childAt);
            }
        }
    }

    public static View layoutId2View(final int layoutId){
        LayoutInflater inflater =
                (LayoutInflater) Utils.getsApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutId,null);
    }
}
