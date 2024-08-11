package com.wk.learn.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.wk.learn.R;
import com.wk.learn.fragment.MainFragment;

public abstract class BaseFragment extends Fragment {
    public Fragment newInstanceAndShow(AppCompatActivity activity) {
        Fragment fragment = getFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_root,fragment)
                .commitAllowingStateLoss();

        return fragment;
    }

    public Fragment newInstance() {
//        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_root,fragment);
        return getFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentView(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);

    protected abstract Fragment getFragment();

    protected abstract int getFragmentView();
}
