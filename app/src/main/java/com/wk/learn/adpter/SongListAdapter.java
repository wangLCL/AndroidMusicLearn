package com.wk.learn.adpter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.adpter.holder.SongListHolder;
import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.MusicInfoBean;
import com.wk.learn.load.SongListLoader;
import com.wk.learn.utils.BitmapUtils;
import com.wk.learn.utils.TimeUtils;

import java.util.ArrayList;

public class SongListAdapter extends RecyclerView.Adapter<SongListHolder> {

    private ArrayList<MusicInfoBean> musicInfoBeans;
    public SongListAdapter(ArrayList<MusicInfoBean> musicInfoBeans) {
        this.musicInfoBeans = musicInfoBeans;
    }

    @NonNull
    @Override
    public SongListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(BaseApplication.context()).inflate(R.layout.song_item,parent,false);
        //分别获取 image view 和 textview 的实例
        SongListHolder songListHolder = new SongListHolder(view);
        view.setTag(songListHolder);
        return songListHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongListHolder holder, int position) {
        MusicInfoBean musicInfoBean = musicInfoBeans.get(position);
        Uri albumArtUri = BitmapUtils.getAlbumArtUri(musicInfoBean.getAlbumId());
        Bitmap bitmap = BitmapUtils.decodeUri(BaseApplication.context(),albumArtUri,300,300);
        holder.getSongIcon().setImageBitmap(bitmap);
        holder.getSongName().setText(musicInfoBean.getTitle());
        holder.getArtName().setText(musicInfoBean.getArtistName());
        holder.getDuration().setText(" · "+TimeUtils.longToTime(musicInfoBean.getDuration()));
        holder.getMoreBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(BaseApplication.context(), v);
                popupMenu.inflate(R.menu.play_item_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicInfoBeans.size();
    }
}
