package com.wk.learn.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.adpter.holder.AlbumListHolder;
import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.AlbumInfoBean;
import com.wk.learn.load.AlbumListLoader;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListHolder> {
    private List<AlbumInfoBean> albumInfoBeanList;
    public AlbumListAdapter(List<AlbumInfoBean> albumInfoBeanList){
        this.albumInfoBeanList = albumInfoBeanList;
    }

    @NonNull
    @Override
    public AlbumListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(BaseApplication.context()).inflate(R.layout.album_item,parent,false);
        AlbumListHolder albumListHolder = new AlbumListHolder(view);
        return albumListHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListHolder holder, int position) {
        AlbumInfoBean albumInfoBean = albumInfoBeanList.get(position);
        System.out.println(albumInfoBean+"======================");
//        holder.getAlbumArt().setImageResource();
        holder.getAlbumName().setText(albumInfoBean.getAlbum());
        holder.getAlbumArtistName().setText(albumInfoBean.getArtist());
    }

    @Override
    public int getItemCount() {
        return albumInfoBeanList.size();
    }
}
