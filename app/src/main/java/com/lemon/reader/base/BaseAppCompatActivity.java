package com.lemon.reader.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lemon.library.kocore.utils.StatusBarCompatUtils;

/**
 * 作者：lemon
 * 日期：2015-09-28
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        beforeSetContentView();
        int layoutResID = getContentViewLayoutId();
        if (layoutResID != 0) {
            setContentView(layoutResID);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        afterSetContentView();

        if (hasStatusBarTint()) {
            int resID = getStatusBarTintResourceId();
            if (resID != 0) {
                StatusBarCompatUtils.setStatusBarTintResource(this, resID);
            } else {
                throw new IllegalArgumentException("You must return a right status bar tint resource Id");
            }
        }

        initView();
    }

    protected abstract void getBundleExtras(Bundle extras);

    protected abstract void beforeSetContentView();

    protected abstract void afterSetContentView();

    protected abstract int getContentViewLayoutId();

    protected abstract void initView();

    protected abstract boolean hasStatusBarTint();

    protected abstract int getStatusBarTintResourceId();

}
