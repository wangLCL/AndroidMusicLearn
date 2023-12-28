package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class OptionCanvasView extends DrawView {
    private float viewWidth;
    private float viewHight;
    public OptionCanvasView(Context context) {
        super(context);
    }

    public OptionCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewHight = h;
        this.viewWidth = w;
    }

    @Override
    protected void drawTest(Canvas canvas) {
//        translate(canvas);

//        rectDemo(canvas);
//        scale(canvas);

        strew(canvas);
    }

    private void strew(Canvas canvas) {
        //错切
    }

    private void rectDemo(Canvas canvas) {
        RectF rectF = new RectF(0,0,300,300);
        canvas.drawRect(rectF,mPaint);

        canvas.translate(viewWidth/2.0f,viewHight/2.0f);
        rectF.set(50,50,150,150);
        canvas.drawRect(rectF,mPaint);

        mPaint.setColor(Color.WHITE);
        //画布的中心点
        canvas.rotate(20);
        canvas.drawRect(rectF,mPaint);


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
