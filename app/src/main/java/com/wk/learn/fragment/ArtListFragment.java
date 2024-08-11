package com.wk.learn.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.wk.learn.R;
import com.wk.learn.fragment.base.BaseFragment;

public class ArtListFragment extends BaseFragment {
    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_art;
    }
}