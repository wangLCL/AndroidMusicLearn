package com.wk.learn.adpter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.application.BaseApplication;
import com.wk.learn.fragment.AboutFragment;
import com.wk.learn.fragment.AlbumDetailFragment;

public class AlbumListHolder extends RecyclerView.ViewHolder {
    private TextView albumArtistName;
    private TextView albumName;
    private ImageView albumArt;
    public AlbumListHolder(@NonNull View itemView, Context context) {
        super(itemView);
        albumArt = itemView.findViewById(R.id.album_art);
        albumName = itemView.findViewById(R.id.album_name);
        albumArtistName = itemView.findViewById(R.id.album_artist_name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                Fragment fragment;

                fragment = new AlbumDetailFragment().newInstance();

                transaction.hide(((AppCompatActivity) context).
                        getSupportFragmentManager().findFragmentById(R.id.content_root));
                transaction.add(R.id.content_root, fragment);
                transaction.addToBackStack(null).commit();
            }
        });
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
