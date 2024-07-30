package com.wk.learn.preference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;

public class KwColorPreference extends Preference {
    private View view;
    private int color;
    private int border;

    public KwColorPreference(Context context){
        this(context,null,0);
    }

    public KwColorPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
