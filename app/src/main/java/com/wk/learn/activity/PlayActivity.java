package com.wk.learn.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wk.learn.R;
import com.wk.learn.bean.MusicInfoBean;
import com.wk.learn.listener.PlayStatusChangeListener;
import com.wk.learn.play.MusicPlay;
import com.wk.learn.utils.SessionUtils;
import com.wk.utilslib.utils.time.TimeUtils;

public class PlayActivity extends AppCompatActivity {
    private Handler timeHandler;
    private SeekBar playSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTop();
        setContentView(R.layout.activity_play);
        showInfo();
        initListener();
        SessionUtils.addPlayStatusChangeListener(new PlayStatusChangeListener() {
            @Override
            public void updateBtnStatus() {
                updatePlayStatus();
            }
        });
        updatePlayStatus();
    }

    private void updatePlayStatus() {
        ImageView playPause = findViewById(R.id.play_pause);
        if (MusicPlay.isPlaying()){
            playPause.setImageResource(R.mipmap.ic_play_arrow_black_36dp);
        }else {
            playPause.setImageResource(R.mipmap.ic_pause_black_36dp);
        }
    }


    private void showInfo() {
        TextView songName       = findViewById(R.id.song_name);
        TextView artName        = findViewById(R.id.artist_name);
        playSeekBar     = findViewById(R.id.play_seekbar);
        TextView songDuration   = findViewById(R.id.song_duration);
        MusicInfoBean musicInfo = MusicPlay.getMusicInfo();
        if (musicInfo!=null){
            songName.setText(musicInfo.getTitle());
            artName.setText(musicInfo.getArtistName());
            songDuration.setText(TimeUtils.minutesAndSecond(musicInfo.getDuration()));
            playSeekBar.setMax(musicInfo.getDuration());
        }
    }

    public void initListener(){
        TextView songPlayTime   = findViewById(R.id.song_play_time);
        ImageView playPause     = findViewById(R.id.play_pause);
        ImageView playNext      = findViewById(R.id.play_next);
        ImageView playPre       = findViewById(R.id.play_pre);
        timeHandler = new Handler();
        timeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long l = MusicPlay.currentPosition();
                songPlayTime.setText(TimeUtils.minutesAndSecond(l));
                timeHandler.postDelayed(this,1000);
                playSeekBar.setProgress((int) l);
            }
        },0);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicPlay.isPlaying()){
                    MusicPlay.pause();
                    playPause.setImageResource(R.mipmap.ic_play_arrow_black_36dp);
                }else {
                    MusicPlay.play();
                    playPause.setImageResource(R.mipmap.ic_pause_black_36dp);
                }
                showInfo();
            }
        });
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlay.playNext();
                showInfo();
            }
        });
        playPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlay.playPre();
                showInfo();
            }
        });
        playSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    MusicPlay.setPosition(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.play_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void hideTop(){
        Window window = getWindow();
        int color = getResources().getColor(R.color.transparent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(color);
            //设置导航栏颜色

            ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置contentview为fitsSystemWindows
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
            //给statusbar着色
            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(this)));
            view.setBackgroundColor(color);
            contentView.addView(view);
        }
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}