package com.lemon.kohttp.callback;

import com.lemon.library.kocore.utils.IOUtils;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

/**
 * 作者：lemon
 * 日期：2015-09-15
 */
public class DownloadCallback extends ResponseCallback {

    private File file;

    public DownloadCallback(File file) {
        this.file = file;
    }

    @Override
    public void onResponse(Response response) throws IOException {

        boolean saved = IOUtils.writeStream(file, response.body().byteStream());
        if (saved) {
            return;
        }
        throw new IOException("can not save file: " + file);
    }

}
