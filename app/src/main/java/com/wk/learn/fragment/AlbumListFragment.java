package com.wk.learn.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.adpter.AlbumListAdapter;
import com.wk.learn.adpter.SongListAdapter;
import com.wk.learn.asyn.CommonLoadAsynTask;
import com.wk.learn.bean.AlbumInfoBean;
import com.wk.learn.fragment.base.BaseFragment;
import com.wk.learn.load.AlbumListLoader;
import com.wk.learn.load.SongListLoader;

import java.util.List;

public class AlbumListFragment extends BaseFragment {
    private List<AlbumInfoBean> albumInfoBeans;
    @Override
    protected void initView(View view) {
        RecyclerView songlistView = findById(R.id.constList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        songlistView.setLayoutManager(gridLayoutManager);
        CommonLoadAsynTask commonLoadAsynTask = new CommonLoadAsynTask(new Runnable() {
            @Override
            public void run() {
                albumInfoBeans = AlbumListLoader.loadAllAlbumList();
            }
        }, new Runnable() {
            @Override
            public void run() {
                songlistView.setAdapter(new AlbumListAdapter(albumInfoBeans,getContext()));
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