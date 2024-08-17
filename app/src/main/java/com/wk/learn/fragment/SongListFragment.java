package com.wk.learn.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.adpter.SongListAdapter;
import com.wk.learn.asyn.CommonLoadAsynTask;
import com.wk.learn.bean.MusicInfoBean;
import com.wk.learn.fragment.base.BaseFragment;
import com.wk.learn.load.SongListLoader;

import java.util.ArrayList;

public class SongListFragment extends BaseFragment {
    private ArrayList<MusicInfoBean> musicInfoBeans;

    @Override
    protected void initView(View view) {
        RecyclerView songlistView = findById(R.id.songlist);
        songlistView.setLayoutManager(new LinearLayoutManager(getContext()));
        CommonLoadAsynTask commonLoadAsynTask = new CommonLoadAsynTask(new Runnable() {
            @Override
            public void run() {
                musicInfoBeans = SongListLoader.loadAllMusic();
            }
        }, new Runnable() {
            @Override
            public void run() {
                songlistView.setAdapter(new SongListAdapter(musicInfoBeans));
            }
        });
        commonLoadAsynTask.execute("");
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
        return R.layout.fragment_song;
    }


}