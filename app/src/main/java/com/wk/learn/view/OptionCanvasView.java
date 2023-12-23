package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class OptionCanvasView extends DrawView {
    public OptionCanvasView(Context context) {
        super(context);
    }

    public OptionCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void drawTest(Canvas canvas) {
        translate(canvas);

        scale(canvas);

    }

    private void scale(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,drawY+100,100,mPaint);
        //缩放中心
        canvas.scale(0.5f,0.5f,200,drawY+100);
        canvas.drawCircle(200,drawY+100,100,mPaint);

//        mPaint.setStyle(Paint.Style.FILL);


        canvas.scale(2.0f,2.0f,200,drawY+100);
        splitLine(canvas);
        canvas.drawCircle(200,drawY+100,100,mPaint);
        for (int i = 1; i <= 20; i++) {
            canvas.scale(0.8f,0.8f,200,drawY+100);
            canvas.drawCircle(200,drawY+100,100,mPaint);
        }
//

    }

    private void translate(Canvas canvas) {
        canvas.drawCircle(200,drawY+100,100,mPaint);

        canvas.translate(100,100);
        canvas.drawCircle(800,drawY+100,100,mPaint);
        canvas.translate(-100,-100);
        splitLine(canvas);

    }
}
