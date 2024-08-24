package com.wk.learn.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.adpter.ArtistListAdapter;
import com.wk.learn.adpter.SongListAdapter;
import com.wk.learn.adpter.holder.ArtistListHolder;
import com.wk.learn.asyn.CommonLoadAsynTask;
import com.wk.learn.bean.ArtistInfoBean;
import com.wk.learn.fragment.base.BaseFragment;
import com.wk.learn.load.ArtistListLoader;
import com.wk.learn.load.SongListLoader;

import java.util.ArrayList;

public class ArtListFragment extends BaseFragment {
    private ArrayList<ArtistInfoBean> musicInfoBeans;

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = findById(R.id.constList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        CommonLoadAsynTask commonLoadAsynTask = new CommonLoadAsynTask(new Runnable() {
            @Override
            public void run() {
                musicInfoBeans = ArtistListLoader.loadArtistAll();
            }
        }, new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new ArtistListAdapter(musicInfoBeans));
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
        return R.layout.fragment_library_content;
    }
}