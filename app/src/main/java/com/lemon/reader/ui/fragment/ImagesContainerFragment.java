package com.lemon.reader.ui.fragment;

import android.support.v4.view.ViewPager;

import com.lemon.reader.R;
import com.lemon.reader.base.BaseFragment;
import com.lemon.reader.bean.BaseEntity;
import com.lemon.reader.ui.adapter.ImagesContainerPagerAdapter;
import com.lemon.reader.widget.XViewPager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ImagesContainerFragment extends BaseFragment{

    @Bind(R.id.viewPager)
    XViewPager viewPager;

    @Bind(R.id.smartTabLayout)
    SmartTabLayout smartTabLayout;

    private ImagesContainerPagerAdapter containerPagerAdapter;

    private List<BaseEntity> categoryList = new ArrayList<BaseEntity>();

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

        String[] imagesCategories = getResources().getStringArray(R.array.images_category_list);
        int size = imagesCategories.length;
        for (int i=0; i<size;i++){
            BaseEntity bean = new BaseEntity();
            bean.name = imagesCategories[i];
            categoryList.add(bean);
        }

        containerPagerAdapter = new ImagesContainerPagerAdapter(getSupportFragmentManager(), categoryList);
        viewPager.setOffscreenPageLimit(size);
        viewPager.setAdapter(containerPagerAdapter);
        smartTabLayout.setViewPager(viewPager);
        smartTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImagesListFragment fragment = (ImagesListFragment) viewPager.getAdapter().instantiateItem(viewPager, position);
                fragment.onPageSelected(position, categoryList.get(position).name);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images;
    }
}
