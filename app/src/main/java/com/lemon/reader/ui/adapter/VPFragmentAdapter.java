package com.lemon.reader.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lemon.reader.base.BaseLazyFragment;

import java.util.List;

public class VPFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseLazyFragment> mListFragments = null;

    public VPFragmentAdapter(FragmentManager fm,List<BaseLazyFragment> fragments) {
        super(fm);
        mListFragments = fragments;
    }

    @Override
    public Fragment getItem(int index) {
        if (mListFragments != null && index > -1 && index < mListFragments.size()) {
            return mListFragments.get(index);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return null != mListFragments ? mListFragments.size() : 0;
    }

}
