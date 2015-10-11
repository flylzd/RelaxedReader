package com.lemon.reader.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cjj.MaterialRefreshLayout;
import com.lemon.reader.R;
import com.lemon.reader.base.BaseFragment;

import butterknife.Bind;

public class ImagesListFragment extends BaseFragment {

    @Bind(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private int currentPage = 0;

    private static String currentImagesCategory = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentImagesCategory = getResources().getStringArray(R.array.images_category_list)[0];
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void initView() {

        RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images_list;
    }


    public void onPageSelected(int position, String keywords) {
        currentImagesCategory = keywords;
        System.out.println("category " + keywords);
    }
}
