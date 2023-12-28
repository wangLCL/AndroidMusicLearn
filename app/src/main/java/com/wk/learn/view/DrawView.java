package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wk.learn.R;

public class DrawView extends View {
    protected Paint mPaint;
    private int splitLineColor = Color.WHITE;
    private int cotentColor = Color.RED;
    protected float drawY=0;

    public DrawView(Context context) {
        this(context,null);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawY = 0;
        //绘制背景
        drawBgColor(canvas);
        drawTest(canvas);
    }

    protected void drawTest(Canvas canvas) {

        //绘制点
        drawPoint(canvas);
        //绘制线
        drawLine(canvas);

        drawRect(canvas);

        drawRoundRect(canvas);

        drawOval(canvas);

        drawCircle(canvas);

        drawArc(canvas);

    }


    private void drawArc(Canvas canvas) {
        RectF rect = new RectF(100,drawY,800,drawY+150);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
//      startAngle   开始角度
//      sweepAngle   扫过角度
//      useCenter    是否使用中心
        //不适应中心，就扫过面积，首尾相连
        canvas.drawArc(rect,0,90,false,mPaint);
        splitLine(canvas);
        RectF rect1 = new RectF(100,drawY,800,drawY+150);
        canvas.drawArc(rect1,0,90,true,mPaint);
        splitLine(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(500,drawY+100,100,mPaint);
        splitLine(canvas);
    }

    private void drawOval(Canvas canvas) {
        canvas.drawOval(100,drawY,400,drawY+160,mPaint);
        RectF rectF = new RectF(500,drawY,800,drawY+160);
        canvas.drawOval(rectF,mPaint);
        splitLine(canvas);
    }

    private void drawRoundRect(Canvas canvas) {
        canvas.drawRoundRect(100,drawY,400,drawY+100,50,50,mPaint);

        RectF rect = new RectF(500, (int) drawY,800, (int) (drawY+100));
        canvas.drawRoundRect(rect,20,20,mPaint);
        splitLine(canvas);
    }

    private void drawRect(Canvas canvas) {
        //绘制矩形
        canvas.drawRect(100,drawY+50,400,drawY+200,mPaint);
        Rect rect = new Rect(500, (int) (drawY),800, (int) (drawY+160));
        canvas.drawRect(rect,mPaint);
        splitLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLines(new float[]{100,drawY+150,100,drawY+200},mPaint);
        splitLine(canvas);
    }

    private void drawPoint(Canvas canvas) {
        //绘制点
        mPaint.setStrokeWidth(40);
        canvas.drawPoint(10,drawY+20,mPaint);
        canvas.drawPoints(new float[]{
                80,drawY+50,
                120,drawY+100
        },mPaint);
        splitLine(canvas);
    }

    private void drawBgColor(Canvas canvas) {
        //绘制背景
        canvas.drawColor(Color.GRAY);
        splitLine(canvas);
    }

    protected void splitLine(Canvas canvas){
        mPaint.setStrokeWidth(5);
        drawY += 200;
        mPaint.setColor(splitLineColor);
        canvas.drawLine(0,drawY,getWidth(),drawY,mPaint);
        mPaint.setColor(cotentColor);
        mPaint.setStrokeWidth(10);
    }
}
