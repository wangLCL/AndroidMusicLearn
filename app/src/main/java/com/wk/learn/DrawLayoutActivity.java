package com.wk.learn;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.wk.learn.fragment.AboutFragment;
import com.wk.learn.fragment.FoldersFragment;
import com.wk.learn.fragment.LibraryFragment;
import com.wk.learn.fragment.PlayListsFragment;
import com.wk.learn.fragment.PlayingQueueFragment;
import com.wk.learn.fragment.SettingFragment;
import com.wk.learn.fragment.SupportDevelopFragment;
import com.wk.learn.fragment.base.BaseFragment;

public class DrawLayoutActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;

    private   Runnable currentViewRunnable = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_layout_main);
        initDrawerLayout();
        defaultView();
    }

    private void defaultView() {
        libraryRunnable.run();
    }

    private void initDrawerLayout() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        View headerView = navView.inflateHeaderView(R.layout.nav_header);
        ImageView imageView = headerView.findViewById(R.id.album_art);
        imageView.setImageResource(R.drawable.ic_empty_music2);

        //设置nav的图标
        setNavIcon(navView);
        setNavListener(navView);

    }

    private void setNavListener(NavigationView navView) {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                updatePosition(item);
                return true;
            }
        });
    }

    private void updatePosition(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_library:
                item.setChecked(true);
                currentViewRunnable =libraryRunnable;
                break;
            case R.id.nav_playlists:
                item.setChecked(true);
                currentViewRunnable = playlistRunnable;
                break;
//            case R.id.nav_folders:
//                currentViewRunnable = foldersRunnable;
//                break;
            case R.id.nav_queue:
                item.setChecked(true);
                currentViewRunnable = playingQueueRunnable;
                break;
//            case R.id.nav_nowplaying:
//                currentViewRunnable = nowPlayingRunnable;
//                break;
            case R.id.nav_settings:
                currentViewRunnable = settingRunnable;
                break;
            case R.id.nav_about:
                currentViewRunnable = aboutRunnable;
                break;
            case R.id.nav_donate:
                currentViewRunnable = supportDevelopRunnable;
                break;
        }
        if (currentViewRunnable !=null) {
            mDrawerLayout.closeDrawers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentViewRunnable.run();
                }
            }, 350);
        }

    }

    private void setNavIcon(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_library).setIcon(R.drawable.library_music);
        navigationView.getMenu().findItem(R.id.nav_playlists).setIcon(R.drawable.playlist_play);
        navigationView.getMenu().findItem(R.id.nav_queue).setIcon(R.drawable.music_note);
//        navigationView.getMenu().findItem(R.id.nav_folders).setIcon(R.drawable.ic_folder_open_black_24dp);
//        navigationView.getMenu().findItem(R.id.nav_nowplaying).setIcon(R.drawable.bookmark_music);
        navigationView.getMenu().findItem(R.id.nav_settings).setIcon(R.drawable.settings);
        navigationView.getMenu().findItem(R.id.nav_about).setIcon(R.drawable.information);
        navigationView.getMenu().findItem(R.id.nav_donate).setIcon(R.drawable.payment_black);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //back按键
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (isNavigatingMain()) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else{
                    super.onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isNavigatingMain() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_root);
        return (currentFragment instanceof BaseFragment);
    }

    /**
     * 库
     */
    private Runnable libraryRunnable = new Runnable() {
        @Override
        public void run() {
            navView.getMenu().findItem(R.id.nav_library).setChecked(true);
            new LibraryFragment().newInstanceAndShow(DrawLayoutActivity.this);
        }
    };

    /**
     * 播放列表
     */
    private Runnable playlistRunnable = new Runnable() {
        @Override
        public void run() {
            new PlayListsFragment().newInstanceAndShow(DrawLayoutActivity.this);
        }
    };


    /**
     * 文件夹
     */
    private Runnable foldersRunnable = new Runnable() {
        @Override
        public void run() {

            new FoldersFragment().newInstanceAndShow(DrawLayoutActivity.this);
        }
    };

    /**
     * 正在播放的队列
     */
    private Runnable playingQueueRunnable = new Runnable() {
        @Override
        public void run() {

            new PlayingQueueFragment().newInstanceAndShow(DrawLayoutActivity.this);
        }
    };
//
//    private Runnable nowPlayingRunnable = new Runnable() {
//        @Override
//        public void run() {
//            navView.getMenu().findItem(R.id.nav_nowplaying).setChecked(false);
//            new PlayListsFragment().newInstanceAndShow(DrawLayoutActivity.this);
//        }
//    };


    private Runnable settingRunnable = new Runnable() {
        @Override
        public void run() {
            new SettingFragment().newInstanceAndShow(DrawLayoutActivity.this);
        }
    };


    private Runnable aboutRunnable = new Runnable() {
        @Override
        public void run() {
            new AboutFragment().newInstanceAndShow(DrawLayoutActivity.this);

        }
    };


    private Runnable supportDevelopRunnable = new Runnable() {
        @Override
        public void run() {
            new SupportDevelopFragment().newInstanceAndShow(DrawLayoutActivity.this);

        }
    };

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
