package com.wk.learn.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ImageUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wk.ateutils.ATE;
import com.wk.ateutils.Config;
import com.wk.learn.R;
import com.wk.learn.adpter.AlbumSongsAdapter;
import com.wk.learn.adpter.ArtistListAdapter;
import com.wk.learn.adpter.SongListAdapter;
import com.wk.learn.asyn.CommonLoadAsynTask;
import com.wk.learn.bean.AlbumBean;
import com.wk.learn.bean.ArtistInfoBean;
import com.wk.learn.bean.MusicInfoBean;
import com.wk.learn.load.ArtistListLoader;
import com.wk.learn.load.SongListLoader;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailFragment extends Fragment {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public static AlbumDetailFragment newInstance() {
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // 重要: 确保调用这个方法
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_detail,container,false);
    }

    private ArrayList<MusicInfoBean> musicInfoBeans;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle("collapsi");


        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        CommonLoadAsynTask commonLoadAsynTask = new CommonLoadAsynTask(new Runnable() {
            @Override
            public void run() {
                musicInfoBeans = SongListLoader.loadAllMusic();
            }
        }, new Runnable() {
            @Override
            public void run() {
                recyclerview.setAdapter(new SongListAdapter(musicInfoBeans));
            }
        });
        commonLoadAsynTask.execute("");
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.album_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
