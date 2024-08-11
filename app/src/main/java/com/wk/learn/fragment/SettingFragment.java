package com.wk.learn.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.wk.learn.R;
import com.wk.learn.fragment.base.BaseFragment;

public class SettingFragment extends BaseFragment {
    @Override
    protected void initView(View view) {
        TextView textView = findById(R.id.fragment_text);
        textView.setText(TAG);
    }

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_library;
    }
}