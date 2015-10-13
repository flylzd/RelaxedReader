package com.lemon.reader.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.lemon.library.kocore.eventbus.EventCenter;
import com.lemon.reader.R;

import butterknife.ButterKnife;

/**
 * 作者：lemon
 * 日期：2015-09-28
 */
public abstract class BaseActivity extends BaseAppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void afterSetContentView() {
//        ButterKnife.bind(this);

        mToolbar = ButterKnife.findById(this, getToolbarID());
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);
//        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
//        if (isBindEventBus()) {
//            EventBus.getDefault().unregister(this);
//        }
    }

    @Override
    protected boolean hasStatusBarTint() {
        return true;
    }

    @Override
    protected int getStatusBarTintResourceId() {
        return R.color.status_bar_color;  //通知栏所需颜色
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    protected int getToolbarID() {
        return R.id.toolbar;
    }


    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    protected void beforeSetContentView() {
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

    }
}
