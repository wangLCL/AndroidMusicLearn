package com.wk.learn.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.adpter.holder.AlbumListHolder;
import com.wk.learn.adpter.holder.ArtistListHolder;
import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.AlbumInfoBean;
import com.wk.learn.bean.ArtistInfoBean;

import java.util.List;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListHolder> {
    private List<ArtistInfoBean> albumInfoBeanList;
    public ArtistListAdapter(List<ArtistInfoBean> albumInfoBeanList){
        this.albumInfoBeanList = albumInfoBeanList;
    }

    @NonNull
    @Override
    public ArtistListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(BaseApplication.context()).inflate(R.layout.artist_item,parent,false);
        ArtistListHolder albumListHolder = new ArtistListHolder(view);
        return albumListHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistListHolder holder, int position) {
        ArtistInfoBean albumInfoBean = albumInfoBeanList.get(position);
        System.out.println(albumInfoBean+"======================");
//        holder.getArtistArt().setImageResource();
        holder.getArtistName().setText(albumInfoBean.getArtist());
        StringBuilder builder = new StringBuilder();
        builder.append(albumInfoBean.getNumberOfAlbums());
        builder.append("张专辑");
        builder.append("|");
        builder.append(albumInfoBean.getNumberOfTracks());
        builder.append("首歌曲");
        holder.getArtistInfo().setText(builder);
    }

    @Override
    public int getItemCount() {
        return albumInfoBeanList.size();
    }
}
