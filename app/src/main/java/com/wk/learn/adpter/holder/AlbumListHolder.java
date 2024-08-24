package com.wk.learn.adpter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;

public class AlbumListHolder extends RecyclerView.ViewHolder {
    private TextView albumArtistName;
    private TextView albumName;
    private ImageView albumArt;
    public AlbumListHolder(@NonNull View itemView) {
        super(itemView);
        albumArt = itemView.findViewById(R.id.album_art);
        albumName = itemView.findViewById(R.id.album_name);
        albumArtistName = itemView.findViewById(R.id.album_artist_name);
    }

    public ImageView getAlbumArt() {
        return albumArt;
    }

    public TextView getAlbumArtistName() {
        return albumArtistName;
    }

    public TextView getAlbumName() {
        return albumName;
    }
}
