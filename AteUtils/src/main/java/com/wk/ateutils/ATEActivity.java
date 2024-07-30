package com.wk.ateutils;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ATEActivity extends AppCompatActivity {

    private long updateTime = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ATE.preApply(this);
        super.onCreate(savedInstanceState);
    }

    private void apply(){
        ATE.apply(this);
        updateTime = System.currentTimeMillis();
    }

    @Override
    public void setContentView(@LayoutRes int layoutId) {
        super.setContentView(layoutId);
        apply();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        apply();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ATE.didValuesChange(this, updateTime)) {
            recreate();
        }
    }

}
