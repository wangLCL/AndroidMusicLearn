package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BezierView extends DrawView {
    private Path path;

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
    }

    @Override
    protected void drawTest(Canvas canvas) {
        path.moveTo(100,100);
        //一阶  lineTo
        //二阶  quadTo
        //三阶  cubicTo
        path.quadTo(100,300,19,10);
        canvas.drawPath(path, mPaint);
    }
}
