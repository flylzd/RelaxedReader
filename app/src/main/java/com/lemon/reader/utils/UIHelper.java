package com.lemon.reader.utils;


import android.app.Activity;
import android.content.Intent;

import com.lemon.reader.ui.activity.ImagesDetailActivity;

public class UIHelper {

    public static void startImagesDetail(Activity activity,String imageUrl, int locationX, int locationY, int width, int height) {
        Intent intent = new Intent(activity, ImagesDetailActivity.class);
        intent.putExtra(ImagesDetailActivity.INTENT_IMAGE_URL_TAG, imageUrl);
        intent.putExtra(ImagesDetailActivity.INTENT_IMAGE_X_TAG, locationX);
        intent.putExtra(ImagesDetailActivity.INTENT_IMAGE_Y_TAG, locationY);
        intent.putExtra(ImagesDetailActivity.INTENT_IMAGE_W_TAG, width);
        intent.putExtra(ImagesDetailActivity.INTENT_IMAGE_H_TAG, height);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

}
