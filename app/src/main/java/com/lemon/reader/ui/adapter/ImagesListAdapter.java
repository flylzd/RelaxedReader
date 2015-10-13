package com.lemon.reader.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemon.library.kocore.eventbus.EventCenter;
import com.lemon.library.kocore.utils.StringUtils;
import com.lemon.reader.AppConstants;
import com.lemon.reader.R;
import com.lemon.reader.bean.ImagesListEntity;
import com.lemon.reader.widget.ScaleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.ViewHolder> {

    private Context context;
    private List<ImagesListEntity> sourceList = new ArrayList<ImagesListEntity>();

    public ImagesListAdapter() {
    }

    public ImagesListAdapter(Context context) {
        this.context = context;
    }

    public ImagesListAdapter(Context context, List<ImagesListEntity> list) {
        this.context = context;
        this.sourceList = list;
    }

    public List<ImagesListEntity> getDataList() {
        return sourceList;
    }

    public ImagesListEntity getItem(int position) {
        return sourceList.size() != 0 ? sourceList.get(position) : null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_images_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        ImagesListEntity itemData = sourceList.get(position);
        int width = itemData.thumbnailWidth;
        int height = itemData.thumbnailHeight;
        String imageUrl = itemData.thumbnailUrl;

        System.out.println("thumbnailUrl = " + imageUrl);
//        holder.itemView

        if (StringUtils.isNotEmpty(imageUrl)) {
            ImageLoader.getInstance().displayImage(imageUrl, holder.itemImage);
            holder.itemImage.setImageWidth(width);
            holder.itemImage.setImageHeight(height);
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    EventCenter<Integer> eventCenter = new EventCenter<Integer>(AppConstants.EventBusCode.EVENT_CODE_IMAGE_DETAIL, position, v);
//                    EventBus.getDefault().post(eventCenter);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return this.sourceList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.image)
        ScaleImageView itemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemImage = (ScaleImageView) itemView.findViewById(R.id.image);
//            itemImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    EventCenter<Integer> eventCenter = new EventCenter<Integer>(AppConstants.EventBusCode.EVENT_CODE_IMAGE_DETAIL, position, v);
//                    EventBus.getDefault().post(eventCenter);
//                }
//            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onItemClickListener){
                onItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
