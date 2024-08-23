package com.wk.learn.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.wk.learn.R;
import com.wk.learn.adpter.ViewpaperPageAdpter;
import com.wk.learn.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends BaseFragment {
    @Override
    protected void initView(View view) {
        TabLayout tabLayout = findById(R.id.tabs);
        ViewPager viewPager = findById(R.id.library_viewpaper);
        tabLayout.setupWithViewPager(viewPager);

        ViewpaperPageAdpter pageAdpter = new ViewpaperPageAdpter(getChildFragmentManager());
        pageAdpter.addFragment(new SongListFragment(),"歌曲");
        pageAdpter.addFragment(new AlbumListFragment(),"专辑");
        pageAdpter.addFragment(new ArtListFragment(),"about");
        viewPager.setAdapter(pageAdpter);
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