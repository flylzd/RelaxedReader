package com.lemon.reader.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemon.reader.R;
import com.lemon.reader.bean.NavigationEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigationAdapter extends BaseAdapter {

    private List<NavigationEntity> sourceList;
    private LayoutInflater layoutInflater;
    private Context context;

    public NavigationAdapter(Context context, List<NavigationEntity> list) {
        this.context = context;
        this.sourceList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.sourceList.size();
    }

    @Override
    public Object getItem(int position) {
        return sourceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.listitem_left_menu, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NavigationEntity bean = sourceList.get(position);
        viewHolder.navigationIcon.setImageResource(bean.iconResId);
        viewHolder.navigationName.setText(bean.name);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.list_item_navigation_icon)
        ImageView navigationIcon;

        @Bind(R.id.list_item_navigation_name)
        TextView navigationName;

        public ViewHolder(View view) {
            ButterKnife.bind(view);
        }
    }
}
