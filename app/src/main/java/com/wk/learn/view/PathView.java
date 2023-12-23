package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * path :
 * 移动起点 --> moveTo
 * 设置终点
 */
public class PathView extends DrawView {
    private float width;
    private float hight;

    private Path path;
    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.hight = h;
    }

    @Override
    protected void drawTest(Canvas canvas) {
        //moveTo  setLastPoint  lineTo  close
//        lineMoveTo(canvas);
//        lineSetPaint(canvas);
        lineClose(canvas);
    }

    private void lineClose(Canvas canvas) {

        path.lineTo(200,200);
        //下次起点是200，200
        path.lineTo(200,0);
        //close
        path.close();

        canvas.drawPath(path,mPaint);
    }

    private void lineSetPaint(Canvas canvas) {
        canvas.translate(width/2.f,hight/2.0f);

        path.lineTo(200,200);
        //下次起点是200，200
        path.lineTo(200,0);

//        canvas.drawPath(path,mPaint);

        //移动下次的起点
//        path.moveTo(-200,-200);
        //会影响前面那个点
        path.setLastPoint(600,200);
        path.lineTo(200,0);

        canvas.drawPath(path,mPaint);

    }

    private void lineMoveTo(Canvas canvas) {
//        画线
        canvas.translate(width/2.f,hight/2.0f);

        path.lineTo(200,200);
        //下次起点是200，200
        path.lineTo(200,0);

        canvas.drawPath(path,mPaint);

        //移动下次的起点
        path.moveTo(-200,-200);

        path.lineTo(200,0);

        canvas.drawPath(path,mPaint);

    }
}
