package com.wk.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DecorContentParent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.ZipUtils;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZipUtils
//        ActivityUtils.g
    }

    public void selfView(View view) {
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
}