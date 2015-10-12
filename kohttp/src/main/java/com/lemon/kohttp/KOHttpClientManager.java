package com.lemon.kohttp;


import com.lemon.kohttp.callback.ResponseCallback;

import java.io.File;
import java.util.Map;

/**
 * 作者：lemon
 * 日期：2015-09-15
 */
public class KOHttpClientManager {


    public static void get(Object tag, String url, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().get(url, responseCallback, tag);
    }

    public static void get(Object tag, String url, Map<String, String> queries, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().get(url, queries, responseCallback, tag);
    }

    public static void get(Object tag, String url, Map<String, String> queries, Map<String, String> headers, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().get(url, queries, headers, responseCallback, tag);
    }

    public static void get(Object tag, String url, RequestParams requestParams, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().get(url, requestParams, responseCallback, tag);
    }

    public static void post(Object tag, String url, Map<String, String> params, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().post(url, params, responseCallback, tag);
    }

    public static void post(Object tag, String url, Map<String, String> params, Map<String, String> headers, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().post(url, params, headers, responseCallback, tag);
    }

    public static void post(Object tag, String url, RequestParams requestParams, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().post(url, requestParams, responseCallback, tag);
    }

    public static void download(Object tag, String url, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().get(url, responseCallback, tag);
    }

    public static void download(Object tag, String url, Map<String, String> queries, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().get(url, queries, responseCallback, tag);
    }

    public static void download(Object tag, String url, RequestParams requestParams, ResponseCallback responseCallback) {
        KOHttpClient.getDefault().get(url, requestParams, responseCallback, tag);
    }

    public static void cancel(Object tag) {
        KOHttpClient.getDefault().cancel(tag);
    }

    public static void setUserAgent(final String userAgent){
        KOHttpClient.getDefault().setUserAgent(userAgent);
    }

}
