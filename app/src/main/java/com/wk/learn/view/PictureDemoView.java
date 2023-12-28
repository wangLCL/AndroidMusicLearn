package com.wk.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/28 15:45
 */
public class PictureDemoView extends View {
    private Picture picture = new Picture();
    public PictureDemoView(Context context) {
        super(context);
    }

    public PictureDemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        recording();
    }

    private void recording(){
        Canvas canvas = picture.beginRecording(500, 500);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100,paint);

        picture.endRecording();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        picture.draw(canvas);
    }
}
