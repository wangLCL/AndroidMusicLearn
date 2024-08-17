package com.wk.learn.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.wk.learn.R;
import com.wk.learn.fragment.MainFragment;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = getClass().getSimpleName();
    private View rootView;

    public Fragment newInstanceAndShow(AppCompatActivity activity) {
        Fragment fragment = getFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_root,fragment)
                .commitAllowingStateLoss();

        return fragment;
    }

    public Fragment newInstance() {
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return rootView = inflater.inflate(getFragmentView(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar();
        initView(view);
    }

    protected void initToolBar() {
        Toolbar toolbar = findById(R.id.app_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(TAG);
    }

    protected abstract void initView(View view);

    protected abstract Fragment getFragment();

    protected abstract int getFragmentView();

    protected <T extends View>  T findById(int id){
        return rootView.findViewById(id);
    }
}
