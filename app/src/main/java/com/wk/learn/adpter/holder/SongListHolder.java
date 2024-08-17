package com.wk.learn.adpter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;

public class SongListHolder extends RecyclerView.ViewHolder {
    private ImageView songIcon;
    private TextView songName;
    private TextView artName;
    private TextView duration;
    private ImageView moreBtn;
    public SongListHolder(@NonNull View itemView) {
        super(itemView);
        songIcon = itemView.findViewById(R.id.song_icon);
        songName = itemView.findViewById(R.id.song_name);
        artName = itemView.findViewById(R.id.art_name);
        duration = itemView.findViewById(R.id.song_time);
        moreBtn = itemView.findViewById(R.id.more_btn);
    }

    public ImageView getMoreBtn() {
        return moreBtn;
    }

    public ImageView getSongIcon() {
        return songIcon;
    }

    public TextView getArtName() {
        return artName;
    }

    public TextView getDuration() {
        return duration;
    }

    public TextView getSongName() {
        return songName;
    }
}
