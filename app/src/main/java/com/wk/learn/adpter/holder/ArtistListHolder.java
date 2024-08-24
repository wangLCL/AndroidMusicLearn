package com.wk.learn.adpter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.play.MusicPlay;

public class ArtistListHolder extends RecyclerView.ViewHolder {
    private final ImageView artistArt;
    private final TextView artistName;
    private final TextView artistInfo;
    public ArtistListHolder(@NonNull View itemView) {
        super(itemView);
        artistArt = itemView.findViewById(R.id.artist_art);
        artistName = itemView.findViewById(R.id.artist_name);
        artistInfo = itemView.findViewById(R.id.artist_info);
    }

    public ImageView getArtistArt() {
        return artistArt;
    }

    public TextView getArtistName() {
        return artistName;
    }

    public TextView getArtistInfo() {
        return artistInfo;
    }
}
