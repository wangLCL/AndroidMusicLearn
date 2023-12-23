package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 扇形  扇形->圆
 */
public class CirView extends View {
    private Paint bgPaint;
    private Paint cirPaint;
    private Path cirPath;

    private float radius = 30;

    private float angle = 0;

    public CirView(Context context) {
        this(context,null);
    }

    public CirView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        bgPaint = new Paint();
        bgPaint.setColor(Color.BLACK);
        bgPaint.setStyle(Paint.Style.FILL);

        cirPaint = new Paint();
        cirPaint.setColor(Color.RED);
        cirPaint.setStyle(Paint.Style.FILL);

        cirPath = new Path();

        postDelayed(new Runnable() {
            @Override
            public void run() {
                angle += 1; // 每次增加5度
                invalidate(); // 重新绘制，触发 onDraw 方法
                if (angle>=360){
                    angle = 360;
                    return;
                }
                postDelayed(this, 10); // 50毫秒后再次执行
            }
        }, 10);

    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int centerX = getWidth()/2;
        int centerY = getHeight()/2;
        int min = Math.min(centerX,centerY);
        canvas.drawCircle(centerX,centerY,min, bgPaint);


        if (angle >= 360) {
            canvas.drawCircle(centerX, centerY, radius, cirPaint);
        }else {
            radius = centerX;
            // 画扇形
            cirPath.moveTo(centerX, centerY);
            cirPath.lineTo(centerX + radius, centerY);
            cirPath.arcTo(centerX - radius, centerY - radius,
                    centerX + radius, centerY + radius,
                    0, angle, true);
            System.out.println(angle + "=======================");
            cirPath.lineTo(centerX, centerY);
            canvas.drawPath(cirPath, cirPaint);
        }
    }
}
