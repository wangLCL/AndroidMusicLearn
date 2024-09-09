package com.wk.learn.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wk.learn.R;
import com.wk.learn.adpter.holder.AlbumListHolder;
import com.wk.learn.application.BaseApplication;
import com.wk.learn.bean.AlbumInfoBean;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListHolder> {
    private List<AlbumInfoBean> albumInfoBeanList;
    private Context context;
    public AlbumListAdapter(List<AlbumInfoBean> albumInfoBeanList, Context context){
        this.albumInfoBeanList = albumInfoBeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(BaseApplication.context()).inflate(R.layout.album_item,parent,false);
        AlbumListHolder albumListHolder = new AlbumListHolder(view,context);
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
