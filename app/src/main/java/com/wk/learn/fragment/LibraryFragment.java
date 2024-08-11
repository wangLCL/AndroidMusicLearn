package com.wk.learn.fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
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

//        Toolbar toolbar = findById(R.id.app_toolbar);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);
//
//        ActionBar supportActionBar = activity.getSupportActionBar();
//        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        supportActionBar.setDisplayHomeAsUpEnabled(true);
        TabLayout tabLayout = findById(R.id.tabs);
        ViewPager viewPager = findById(R.id.library_viewpaper);
        tabLayout.setupWithViewPager(viewPager);

//        A adapter = new Adapter(getChildFragmentManager());
//        adapter.addFragment(new SongsFragment(), this.getString(R.string.songs));
//        adapter.addFragment(new AlbumFragment(), this.getString(R.string.albums));
//        adapter.addFragment(new ArtistFragment(), this.getString(R.string.artists));
//        viewPager.setAdapter(adapter);

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