package com.wk.learn.fragment;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.TextView;

import com.wk.learn.R;
import com.wk.learn.fragment.base.BaseFragment;

public class PlayListsFragment extends BaseFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_plist;
    }
}