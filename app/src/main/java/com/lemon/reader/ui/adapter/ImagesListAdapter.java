package com.lemon.reader.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lemon.library.kocore.utils.StringUtils;
import com.lemon.reader.R;
import com.lemon.reader.bean.ImagesListEntity;
import com.lemon.reader.widget.ScaleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.ViewHolder> {

    private Context context;
    private List<ImagesListEntity> sourceList = new ArrayList<ImagesListEntity>();

    public ImagesListAdapter() {
    }

    public ImagesListAdapter(Context context, List<ImagesListEntity> list) {
        this.context = context;
        this.sourceList = list;
    }

    public List<ImagesListEntity> getDataList() {
        return  sourceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_images_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImagesListEntity itemData = sourceList.get(position);
        int width = itemData.thumbnailWidth;
        int height = itemData.thumbnailHeight;
        String imageUrl = itemData.thumbnailUrl;

        if (StringUtils.isNotEmpty(imageUrl)){

        }
    }

    @Override
    public int getItemCount() {
        return this.sourceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ScaleImageView itemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
