package com.wk.ateutils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wk.ateutils.ATE;

public class ATETextView extends TextView {
    public ATETextView(Context context) {
        this(context,null);
    }

    public ATETextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public ATETextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ATE.apply(context,this);
    }
}