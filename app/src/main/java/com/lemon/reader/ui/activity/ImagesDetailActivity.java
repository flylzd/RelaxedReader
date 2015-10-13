package com.lemon.reader.ui.activity;


import android.os.Bundle;
import android.view.View;

import com.lemon.library.kocore.eventbus.EventCenter;
import com.lemon.library.kocore.widget.SmoothImageView;
import com.lemon.reader.R;
import com.lemon.reader.base.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImagesDetailActivity extends BaseActivity {

    public static final String INTENT_IMAGE_URL_TAG = "INTENT_IMAGE_URL_TAG";
    public static final String INTENT_IMAGE_X_TAG = "INTENT_IMAGE_X_TAG";
    public static final String INTENT_IMAGE_Y_TAG = "INTENT_IMAGE_Y_TAG";
    public static final String INTENT_IMAGE_W_TAG = "INTENT_IMAGE_W_TAG";
    public static final String INTENT_IMAGE_H_TAG = "INTENT_IMAGE_H_TAG";

    private String mImageUrl;
    private int mLocationX;
    private int mLocationY;
    private int mWidth;
    private int mHeight;

    @Bind(R.id.images_detail_smooth_image)
    SmoothImageView smoothImageView;

    @Override
    protected void getBundleExtras(Bundle extras) {
        mImageUrl = extras.getString(INTENT_IMAGE_URL_TAG);
        mLocationX = extras.getInt(INTENT_IMAGE_X_TAG);
        mLocationY = extras.getInt(INTENT_IMAGE_Y_TAG);
        mWidth = extras.getInt(INTENT_IMAGE_W_TAG);
        mHeight = extras.getInt(INTENT_IMAGE_H_TAG);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_images_detail;
    }

    @Override
    protected void initView() {

        smoothImageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        smoothImageView.transformIn();

        ImageLoader.getInstance().displayImage(mImageUrl, smoothImageView);

        smoothImageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });

        smoothImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v2) {
                smoothImageView.transformOut();
            }
        });

    }

    @Override
    public void onBackPressed() {
        smoothImageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }
}
