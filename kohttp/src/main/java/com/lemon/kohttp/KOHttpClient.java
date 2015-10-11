package com.lemon.kohttp;


import com.lemon.kohttp.callback.ResponseCallback;
import com.lemon.kohttp.progress.ProgressInterceptor;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/**
 * 作者：lemon
 * 日期：2015-09-14
 */
public class KOHttpClient {

    static class SingletonHolder {
        static KOHttpClient INSTANCE = new KOHttpClient();
    }

    public static KOHttpClient getDefault() {
        return SingletonHolder.INSTANCE;
    }

    public static final String TAG = KOHttpClient.class.getSimpleName();

    private boolean isDebug = BuildConfig.DEBUG;
    private final OkHttpClient mClient;
    //    private HttpConfig mHttpConfig;
    private OkClientInterceptor mInterceptor;

    //    private Map<String, String> mParams;
    private Map<String, String> mHeaders;


    public KOHttpClient() {
        mClient = new OkHttpClient();
        mClient.setFollowRedirects(true);
//        mParams = new NoEmptyValuesHashMap();
        mHeaders = new NoEmptyValuesHashMap();
    }


    public KOHttpClient setInterceptor(final OkClientInterceptor interceptor) {
        mInterceptor = interceptor;
        return this;
    }

    public KOHttpClient setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        mClient.setHostnameVerifier(hostnameVerifier);
        return this;
    }

    public KOHttpClient setSocketFactory(SocketFactory socketFactory) {
        mClient.setSocketFactory(socketFactory);
        return this;
    }

    public KOHttpClient setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
        mClient.setSslSocketFactory(sslSocketFactory);
        return this;
    }

    public KOHttpClient setFollowRedirects(boolean followRedirects) {
        mClient.setFollowRedirects(followRedirects);
        return this;
    }

    public KOHttpClient setFollowSslRedirects(boolean followProtocolRedirects) {
        mClient.setFollowSslRedirects(followProtocolRedirects);
        return this;
    }

    public KOHttpClient setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
        mClient.setRetryOnConnectionFailure(retryOnConnectionFailure);
        return this;
    }

    public int getConnectTimeout() {
        return mClient.getConnectTimeout();
    }

    public int getReadTimeout() {
        return mClient.getReadTimeout();
    }

    public int getWriteTimeout() {
        return mClient.getWriteTimeout();
    }

    public KOHttpClient setConnectTimeout(long timeout, TimeUnit unit) {
        mClient.setConnectTimeout(timeout, unit);
        return this;
    }

    public KOHttpClient setReadTimeout(long timeout, TimeUnit unit) {
        mClient.setReadTimeout(timeout, unit);
        return this;
    }

    public KOHttpClient setWriteTimeout(long timeout, TimeUnit unit) {
        mClient.setWriteTimeout(timeout, unit);
        return this;
    }


    public String getHeader(final String key) {
//        AssertUtils.notEmpty(key, "key must not be null or empty.");
        return mHeaders.get(key);
    }

    public KOHttpClient addHeader(final String key, final String value) {
//        AssertUtils.notEmpty(key, "key must not be null or empty.");
        mHeaders.put(key, value);
        return this;
    }

    public KOHttpClient addHeaders(final Map<String, String> headers) {
//        AssertUtils.notNull(headers, "headers must not be null.");
        mHeaders.putAll(headers);
        return this;
    }

    public KOHttpClient removeHeader(final String key) {
//        AssertUtils.notEmpty(key, "key must not be null or empty.");
        mHeaders.remove(key);
        return this;
    }

    public int getHeadersSize() {
        return mHeaders.size();
    }

    public String getUserAgent() {
        return getHeader(HttpConsts.USER_AGENT);
    }

    public KOHttpClient setUserAgent(final String userAgent) {
        if (userAgent == null) {
            removeHeader(HttpConsts.USER_AGENT);
        } else {
            addHeader(HttpConsts.USER_AGENT, userAgent);
        }
        return this;
    }

    public String getAuthorization() {
        return getHeader(HttpConsts.AUTHORIZATION);
    }

    public KOHttpClient setAuthorization(final String authorization) {
        addHeader(HttpConsts.AUTHORIZATION, authorization);
        return this;
    }

    public KOHttpClient removeAuthorization() {
        removeHeader(HttpConsts.AUTHORIZATION);
        return this;
    }

    public String getRefer() {
        return getHeader(HttpConsts.REFERER);
    }

    public KOHttpClient setReferer(final String referer) {
        addHeader(HttpConsts.REFERER, referer);
        return this;
    }

    public KOHttpClient cancel(Object tag) {
        mClient.cancel(tag);
        return this;
    }


    /**
     * ********************************************************
     * HTTP REQUEST METHODS
     * **********************************************************
     */

    public void head(final String url, final ResponseCallback responseCallback,
                     final Object tag) throws IOException {
        head(url, null, responseCallback, tag);
    }

    public void head(final String url, final Map<String, String> queries, final ResponseCallback responseCallback,
                     final Object tag) {
        head(url, queries, null, responseCallback, tag);
    }

    public void head(final String url,
                     final Map<String, String> queries,
                     final Map<String, String> headers,
                     final ResponseCallback responseCallback,
                     final Object tag) {
        request(HttpMethod.HEAD, url, queries, null, headers, responseCallback, tag);
    }

    public void get(final String url, final ResponseCallback responseCallback,
                    final Object tag) {
        get(url, null, null, responseCallback, tag);
    }

    public void get(final String url, final Map<String, String> queries, final ResponseCallback responseCallback,
                    final Object tag) {
        get(url, queries, null, responseCallback, tag);
    }

    public void get(final String url,
                    final Map<String, String> queries,
                    final Map<String, String> headers,
                    final ResponseCallback responseCallback,
                    final Object tag) {
        request(HttpMethod.GET, url, queries, null, headers, responseCallback, tag);
    }


    public void post(final String url,
                     final Map<String, String> params,
                     final ResponseCallback responseCallback,
                     final Object tag) {
        post(url, params, null, responseCallback, tag);
    }

    public void post(final String url,
                     final Map<String, String> params,
                     final Map<String, String> headers,
                     final ResponseCallback responseCallback,
                     final Object tag) {
        request(HttpMethod.POST, url, null, params, headers, responseCallback, tag);
    }


    public void put(final String url, final Map<String, String> params, final ResponseCallback responseCallback,
                    final Object tag) {
        put(url, params, null, responseCallback, tag);
    }

    public void put(final String url,
                    final Map<String, String> params,
                    final Map<String, String> headers,
                    final ResponseCallback responseCallback,
                    final Object tag) {
        request(HttpMethod.PUT, url, null, params, headers, responseCallback, tag);
    }

    // put params into url queries
    public void delete(final String url, final ResponseCallback responseCallback,
                       final Object tag) {
        deleteByQueryParams(url, null, null, responseCallback, tag);
    }

    // put params into url queries
    public void deleteByQueryParams(final String url, final Map<String, String> queries, final ResponseCallback responseCallback,
                                    final Object tag) {
        deleteByQueryParams(url, queries, null, responseCallback, tag);
    }

    // put params into url queries
    public void deleteByQueryParams(final String url,
                                    final Map<String, String> queries,
                                    final Map<String, String> headers,
                                    final ResponseCallback responseCallback,
                                    final Object tag) {
        request(HttpMethod.DELETE, url, queries, null, headers, responseCallback, tag);
    }

    // put params into  http request body
    public void deleteByBodyParams(final String url, final Map<String, String> params, final ResponseCallback responseCallback,
                                   final Object tag) {
        deleteByBodyParams(url, params, null, responseCallback, tag);
    }

    // put params into  http request body
    public void deleteByBodyParams(final String url,
                                   final Map<String, String> params,
                                   final Map<String, String> headers,
                                   final ResponseCallback responseCallback,
                                   final Object tag) {
        request(HttpMethod.DELETE, url, null, params, headers, responseCallback, tag);
    }


    public void get(final String url, final RequestParams params, final ResponseCallback responseCallback,
                    final Object tag) {
        request(HttpMethod.GET, url, params, responseCallback, tag);
    }

    public void delete(final String url, final RequestParams params, final ResponseCallback responseCallback,
                       final Object tag) {
        request(HttpMethod.DELETE, url, params, responseCallback, tag);
    }

    public void post(final String url, final RequestParams params, final ResponseCallback responseCallback,
                     final Object tag) {
        request(HttpMethod.POST, url, params, responseCallback, tag);
    }

    public void put(final String url, final RequestParams params, final ResponseCallback responseCallback,
                    final Object tag) {
        request(HttpMethod.PUT, url, params, responseCallback, tag);
    }


    public void request(final HttpMethod method, final String url,
                        final Map<String, String> queries,
                        final Map<String, String> forms,
                        final Map<String, String> headers,
                        final ResponseCallback responseCallback,
                        final Object tag
    ) {
        callRequest(createRequest(method, url,
                queries, forms, headers, tag), responseCallback);
    }

    public void request(final HttpMethod method, final String url,
                        final RequestParams requestParams,
                        final ResponseCallback responseCallback,
                        final Object tag) {
        callRequest(createRequest(method, url, requestParams, tag), responseCallback);
    }

    protected void callRequest(final KORequest xRequest, final ResponseCallback responseCallback) {

        final Request request;
        request = createOkRequest(xRequest);

        final OkHttpClient client = mClient.clone();

//        System.out.println("");
        Logger.d("isDebug == " + isDebug);
        if (isDebug) {
            // intercept for logging
            client.networkInterceptors().add(new LoggingInterceptor());
        }

        client.networkInterceptors().add(new ProgressInterceptor(responseCallback));
        // intercept for progress callback
//        if (xRequest.listener() != null) {
//            client.interceptors().add(new ProgressInterceptor(nr.listener()));
//        }
        if (mInterceptor != null) {
            mInterceptor.intercept(client);
        }
        responseCallback.onStart();
        client.newCall(request).enqueue(responseCallback);
    }

    protected KORequest createRequest(final HttpMethod method, final String url,
                                      final RequestParams params, final Object tag) {
        final KORequest request = new KORequest(method, url, tag)
                .headers(mHeaders);
        return request.params(params);
    }

    protected KORequest createRequest(final HttpMethod method, final String url,
                                      final Map<String, String> queries,
                                      final Map<String, String> forms,
                                      final Map<String, String> headers,
                                      final Object tag) {
        final KORequest request = new KORequest(method, url, tag)
                .headers(mHeaders);
        if (request.supportBody()) {
            request.forms(forms);
        }
        return request.headers(headers).queries(queries);
    }

    protected Request createOkRequest(final KORequest request) {
        return new Request.Builder()
                .url(request.url())
                .headers(Headers.of(request.headers()))
                .method(request.method().name(), request.getRequestBody())
                .tag(request.getTag())
                .build();
    }

}
