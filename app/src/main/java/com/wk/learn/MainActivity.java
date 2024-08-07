package com.wk.learn;

import androidx.annotation.FloatRange;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DecorContentParent;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.RomUtils;
import com.blankj.utilcode.util.ViewUtils;
import com.blankj.utilcode.util.VolumeUtils;
import com.blankj.utilcode.util.ZipUtils;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        @FloatRange(from = 0,to = 1)
//        float i = 0;
//        BarUtils
//        AppUtils
//        getResources().getDisplayMetrics().widthPixels * 72f /
//        AdaptScreenUtils;
//        RomUtils
//        BarUtils
//        AppUtils
//        android.os.SystemProperties
//        ActivityUtils.g
        //720  281.353

//        System.out.println(getResources().getDisplayMetrics().widthPixels);
//        System.out.println(getResources().getDisplayMetrics().xdpi);

//        getBaseContext().getTheme().obtainStyledAttributes()


        // 获取启动该 Activity 的 Intent
        Intent intent = getIntent();

        // 判断 Intent 的 action 是否是 MAIN，并且 category 包含 LAUNCHER
        if (Intent.ACTION_MAIN.equals(intent.getAction()) && intent.hasCategory(Intent.CATEGORY_LAUNCHER)) {
            // 表示是从应用图标点击启动的
            // 可以在这里进行相应的处理
            System.out.println("图标启动");
        }
    }

    public void selfView(View view) {
        float density = getResources().getDisplayMetrics().density;
        BarUtils.getStatusBarHeight();
        AdaptScreenUtils.adaptWidth(getResources(),720);
        Intent intent = new Intent(this,SelfActivity.class);
        startActivity(intent);
    }

    public void drawColor(View view) {

        Intent intent = new Intent(this,DrawColorActivity.class);
        startActivity(intent);
    }

    public void optionCanvas(View view) {
        Intent intent = new Intent(this,CanvasOptionActivity.class);
        startActivity(intent);
    }

    public void drawPicture(View view) {
        Intent intent = new Intent(this,DrawPictureAndText.class);
        startActivity(intent);
    }

    public void basePath(View view) {
        Intent intent = new Intent(this,PathActivity.class);
        startActivity(intent);
    }

    public void bezierPath(View view) {
        Intent intent = new Intent(this,BezierActivity.class);
        startActivity(intent);
    }

    public void pieCanvas(View view) {
        Intent intent = new Intent(this,PieActivity.class);
        startActivity(intent);
    }

    public void pictureDraw(View view) {
        openActivity(PictureTextureActivity.class);
    }

    public void openActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }

    public void toSetting(View view) {
        openActivity(SettingActivity.class);
    }
}