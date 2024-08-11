package com.wk.learn.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wk.learn.R;
import com.wk.learn.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends BaseFragment {
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