package com.lemon.kohttp.callback;


import android.os.Handler;
import android.os.Looper;

import com.lemon.kohttp.progress.ProgressListener;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class ResponseCallback implements Callback, ProgressListener {

//    protected static final int SUCCESS_MESSAGE = 0;
//    protected static final int FAILURE_MESSAGE = 1;
//    protected static final int START_MESSAGE = 2;
//    protected static final int FINISH_MESSAGE = 3;

    protected Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(Request request, IOException e) {
        onError(request, e);
    }

    @Override
    public void onResponse(Response response) throws IOException {

    }

    @Override
    public void onProgress(long currentBytes, long contentLength, boolean done) {
    }

    public void onStart() {
    }

    public void onFinish() {
    }

    public void onError(Request request, Exception e) {
    }

    public void onSuccess(int statusCode, Object response) {

    }

    protected void sendOnSuccessMessage(final int statusCode, final Object response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(statusCode, response);
                onFinish();
            }
        });
    }

    protected void sendOnErrorMessage(final Request request, final Exception e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
//                    onSuccess(response, responseString);
                onError(request, e);
                onFinish();
            }
        });
    }

}
