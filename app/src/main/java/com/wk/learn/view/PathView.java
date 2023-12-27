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
//        lineClose(canvas);
        adSrc(canvas);
    }

    private void adSrc(Canvas canvas) {
//        // 第一类(基本形状)
//        // 圆形
//        public void addCircle (float x, float y, float radius, Path.Direction dir)
//        // 椭圆
//        public void addOval (RectF oval, Path.Direction dir)
//        // 矩形
//        public void addRect (float left, float top, float right, float bottom, Path.Direction dir)
//        public void addRect (RectF rect, Path.Direction dir)
//        // 圆角矩形
//        public void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
//        public void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
        canvas.translate(width / 2, hight / 2);  // 移动坐标系到屏幕中心
        path.addRect(-200,-200,200,200, Path.Direction.CW);
        canvas.drawPath(path,mPaint);
//        对于CW的作用，就是指定顶点的使用顺序

//        第二类
        // path
//        public void addPath (Path src)
//        先将图形移动后在加进去
//        public void addPath (Path src, float dx, float dy)
//        先进行变换再加进去
//        public void addPath (Path src, Matrix matrix)


//        第三类(addArc与arcTo)
        // 第三类(addArc与arcTo)
        // addArc
//        public void addArc (RectF oval, float startAngle, float sweepAngle)
//        // arcTo
//        public void arcTo (RectF oval, float startAngle, float sweepAngle)
//        public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
//        addArc	添加一个圆弧到path	直接添加一个圆弧到path中
//        arcTo	添加一个圆弧到path	添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点

//        forceMoveTo是什么作用呢？
//
//        这个变量意思为“是否强制使用moveTo”，也就是说，是否使用moveTo将变量移动到圆弧的起点位移，也就意味着：
//
//        forceMoveTo	含义	等价方法
//        true	将最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点	public void addArc (RectF oval, float startAngle, float sweepAngle)
//        false	不移动，而是连接最后一个点与圆弧起点	public void arcTo (RectF oval, float startAngle, float sweepAngle)


    }

    private void lineClose(Canvas canvas) {

        path.lineTo(200,200);
        //下次起点是200，200
        path.lineTo(200,0);
        //close闭环
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
