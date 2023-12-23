package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PictureAndTextDrawView extends DrawView {
    public PictureAndTextDrawView(Context context) {
        super(context);
    }

    public PictureAndTextDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawTest(Canvas canvas) {
//        Picture
    }
}
