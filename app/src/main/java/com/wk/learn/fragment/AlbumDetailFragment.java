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
import com.wk.learn.bean.AlbumBean;

import java.util.List;

public class AlbumDetailFragment extends Fragment {
    private long albumID = -1;
    private ImageView albumArt, artistArt;
    private TextView albumTitle, albumDetails;
    private AppCompatActivity mContext;

    private RecyclerView recyclerView;
    private AlbumSongsAdapter mAdapter;

    private Toolbar toolbar;

    private AlbumBean album;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private FloatingActionButton fab;

    private boolean loadFailed = false;

    private Context context;
    private int primaryColor = -1;

    public static AlbumDetailFragment newInstance(long id, boolean useTransition, String transitionName) {
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.ALBUM_ID, id);
        args.putBoolean("transition", useTransition);
        if (useTransition)
            args.putString("transition_name", transitionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            albumID = getArguments().getLong(Constants.ALBUM_ID);
        }
        context = getActivity();
        mContext = (AppCompatActivity) context;
    }

    @TargetApi(21)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.fragment_album_detail, container, false);

        albumArt = (ImageView) rootView.findViewById(R.id.album_art);
        artistArt = (ImageView) rootView.findViewById(R.id.artist_art);
        albumTitle = (TextView) rootView.findViewById(R.id.album_title);
        albumDetails = (TextView) rootView.findViewById(R.id.album_details);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        if (getArguments().getBoolean("transition")) {
            albumArt.setTransitionName(getArguments().getString("transition_name"));
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        appBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        recyclerView.setEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        album = AlbumLoader.getAlbum(getActivity(), albumID);

//        setAlbumart();

        setUpEverything();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        AlbumSongsAdapter adapter = (AlbumSongsAdapter) recyclerView.getAdapter();
//                        MusicPlayer.playAll(getActivity(), adapter.getSongIds(), 0, albumID, TimberUtils.IdType.Album, true);
//                        NavigationUtils.navigateToNowplaying(getActivity(), false);
                    }
                }, 150);
            }
        });

        return rootView;
    }

    private void setupToolbar() {

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(album.title);

    }


    private void setAlbumDetails() {

        String songCount = TimberUtils.makeLabel(getActivity(), R.plurals.Nsongs, album.songCount);

        String year = (album.year != 0) ? (" - " + String.valueOf(album.year)) : "";

        albumTitle.setText(album.title);
        albumDetails.setText(album.artistName + " - " + songCount + year);


    }

    private void setUpAlbumSongs() {

        List<Song> songList = AlbumSongLoader.getSongsForAlbum(getActivity(), albumID);
        mAdapter = new AlbumSongsAdapter(getActivity(), songList, albumID);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter);

    }

    private void setUpEverything() {
        setupToolbar();
        setAlbumDetails();
        setUpAlbumSongs();
    }

    private void reloadAdapter() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... unused) {
                List<Song> songList = AlbumSongLoader.getSongsForAlbum(getActivity(), albumID);
                mAdapter.updateDataSet(songList);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.album_detail, menu);
        if (getActivity() != null)
            ATE.applyMenu(getActivity(), "dark_theme", menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_go_to_artist:
                NavigationUtils.goToArtist(getContext(), album.artistId);
                break;
            case R.id.popup_song_addto_queue:
                MusicPlayer.addToQueue(context, mAdapter.getSongIds(), -1, TimberUtils.IdType.NA);
                break;
            case R.id.popup_song_addto_playlist:
                AddPlaylistDialog.newInstance(mAdapter.getSongIds()).show(mContext.getSupportFragmentManager(), "ADD_PLAYLIST");
                break;
            case R.id.menu_sort_by_az:
                mPreferences.setAlbumSongSortOrder(SortOrder.AlbumSongSortOrder.SONG_A_Z);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_za:
                mPreferences.setAlbumSongSortOrder(SortOrder.AlbumSongSortOrder.SONG_Z_A);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_year:
                mPreferences.setAlbumSongSortOrder(SortOrder.AlbumSongSortOrder.SONG_YEAR);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_duration:
                mPreferences.setAlbumSongSortOrder(SortOrder.AlbumSongSortOrder.SONG_DURATION);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_track_number:
                mPreferences.setAlbumSongSortOrder(SortOrder.AlbumSongSortOrder.SONG_TRACK_LIST);
                reloadAdapter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        String ateKey = Helpers.getATEKey(getActivity());
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        if (primaryColor != -1 && getActivity() != null) {
            collapsingToolbarLayout.setContentScrimColor(primaryColor);
            ATEUtils.setFabBackgroundTint(fab, primaryColor);
            ATEUtils.setStatusBarColor(getActivity(), ateKey, primaryColor);
        }

    }

    private class EnterTransitionListener extends SimplelTransitionListener {

        @TargetApi(21)
        public void onTransitionEnd(Transition paramTransition) {
            FabAnimationUtils.scaleIn(fab);
        }

        public void onTransitionStart(Transition paramTransition) {
            FabAnimationUtils.scaleOut(fab, 0, null);
        }

    }
}
