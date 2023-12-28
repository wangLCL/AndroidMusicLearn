package com.wk.learn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wk.learn.bean.PieData;

import java.util.ArrayList;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/28 12:17
 */
public class PieView extends View {
    private Paint mPaint;
    private float width;
    private float height;
    private float currentAngle;

    private int[] mColors = {
            0xFFCCFF00, 0xFF6495ED,
            0xFFE32636, 0xFF800000};
    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.translate(width/2.0f,height/2.0f);
        float min = Math.min(width, height);
        min = min/2.0f;
        RectF rect = new RectF(-min,-min,min,min);
        for (int i = 0; i < 4; i++) {
            mPaint.setColor(mColors[i]);
            canvas.drawArc(rect,currentAngle+= 90,90,true,mPaint);
        }
    }
}
