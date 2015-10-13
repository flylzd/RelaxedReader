package com.lemon.reader.ui.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.lemon.kohttp.callback.GsonCallback;
import com.lemon.library.kocore.eventbus.EventCenter;
import com.lemon.library.kocore.utils.NetworkUtils;
import com.lemon.reader.R;
import com.lemon.reader.api.ApiClient;
import com.lemon.reader.base.BaseFragment;
import com.lemon.reader.bean.ResponseImagesListEntity;
import com.lemon.reader.ui.adapter.ImagesListAdapter;
import com.lemon.reader.AppConstants;
import com.lemon.reader.utils.UIHelper;

import butterknife.Bind;

public class ImagesListFragment extends BaseFragment {

    @Bind(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    //    private ImagesListAdapter adapter = new ImagesListAdapter(getActivity());
    private ImagesListAdapter adapter;

    private int currentPage = 0;
    private static String currentImagesCategory = null;
    private int eventTag = AppConstants.EVENT_REFRESH_DATA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentImagesCategory = getResources().getStringArray(R.array.images_category_list)[0];
    }

    @Override
    protected void onFirstUserVisible() {

        boolean isConnected = NetworkUtils.isNetworkConnected(getActivity());
        if (isConnected) {
            getCommonListData(TAG, AppConstants.EVENT_REFRESH_DATA, currentImagesCategory, currentPage);
        } else {

        }
    }

    @Override
    protected void onUserVisible() {
    }

    @Override
    protected void onUserInvisible() {
    }

    @Override
    protected void initView() {

        adapter = new ImagesListAdapter(getActivity());
        adapter.setOnItemClickListener(new ImagesListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Rect frame = new Rect();
                getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;

                int[] location = new int[2];
                view.getLocationOnScreen(location);
                location[1] += statusBarHeight;

                int width = view.getWidth();
                int height = view.getHeight();
                String imageUrl = adapter.getDataList().get(position).thumbnailUrl;
                UIHelper.startImagesDetail(getActivity(),imageUrl,location[0],location[1],width,height);
            }
        });
        RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);

        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.autoRefreshLoadMore();
        materialRefreshLayout.finishRefreshLoadMore();
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {

            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                currentPage = 0;
                getCommonListData(TAG, AppConstants.EVENT_REFRESH_DATA, currentImagesCategory, currentPage);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                currentPage++;
                getCommonListData(TAG, AppConstants.EVENT_LOAD_MORE_DATA, currentImagesCategory, currentPage);
            }
        });
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {
        System.out.println("EventCode " + eventCenter.getEventCode());
        if (eventCenter.getEventCode() == AppConstants.EventBusCode.EVENT_CODE_IMAGE_DETAIL) {
            View view = eventCenter.getView();
            if (null != view) {
                Rect frame = new Rect();
                getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;

                int[] location = new int[2];
                view.getLocationOnScreen(location);
                location[1] += statusBarHeight;

                int width = view.getWidth();
                int height = view.getHeight();

                int position = (int) eventCenter.getData();
                System.out.println("position " + position);
//                String imageUrl = adapter.getDataList().get(0).imageUrl;
                String imageUrl = adapter.getItem(position).imageUrl;
                UIHelper.startImagesDetail(getActivity(),imageUrl,location[0],location[1],width,height);
            }
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images_list;
    }

    public void onPageSelected(int position, String keywords) {
        currentImagesCategory = keywords;
        System.out.println("category " + keywords);
    }

    private void getCommonListData(final String requestTag, int eventTag, String keywords, int page) {
        this.eventTag = eventTag;
        ApiClient.getImagesList(requestTag, keywords, page, new ResultCallback());
    }

    class ResultCallback extends GsonCallback<ResponseImagesListEntity> {

        @Override
        public void onSuccess(ResponseImagesListEntity response) {
            System.out.println("response " + response);
            if (null == response) {
                return;
            }

            if (eventTag == AppConstants.EVENT_REFRESH_DATA) {
                adapter.getDataList().clear();
                adapter.getDataList().addAll(response.imgs);
                adapter.notifyDataSetChanged();
            } else if (eventTag == AppConstants.EVENT_LOAD_MORE_DATA) {
                adapter.getDataList().addAll(response.imgs);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFinish() {
            materialRefreshLayout.finishRefresh();
            materialRefreshLayout.finishRefreshLoadMore();
        }
    }

}
