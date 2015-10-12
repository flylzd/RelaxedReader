package com.lemon.reader.api;


import com.lemon.kohttp.KOHttpClientManager;
import com.lemon.kohttp.callback.ResponseCallback;
import com.lemon.reader.AppContext;

public class ApiClient {

    static {
        KOHttpClientManager.setUserAgent(ApiClientHelper.getUserAgent(AppContext.getInstance()));
    }

    public static void getImagesList(String requestTag, String keywords, int page, ResponseCallback responseCallback) {
        String url = UriHelper.getInstance().getImagesListUrl(keywords, page);
        KOHttpClientManager.get(requestTag, url, responseCallback);
    }



}
