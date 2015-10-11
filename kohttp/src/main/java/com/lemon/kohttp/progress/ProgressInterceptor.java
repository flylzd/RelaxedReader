package com.lemon.kohttp.progress;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * 作者：lemon
 * 日期：2015-09-15
 */
public class ProgressInterceptor implements Interceptor {
    private ProgressListener listener;

    public ProgressInterceptor(final ProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(final Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new ProgressResponseBody(response.body(), listener))
                .build();
    }
}
