package com.wk.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setSupportActionBar(findViewById(R.id.app_toolbar));
//        当你希望在ActionBar（或Toolbar）中显示一个返回箭头时，可以通过调用 setDisplayHomeAsUpEnabled(true) 来实现
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //尽量设置页面
        if (savedInstanceState == null)
            getFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new SettingsFragment()).commit();
    }
}