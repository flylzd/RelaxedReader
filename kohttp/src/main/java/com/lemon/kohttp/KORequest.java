package com.lemon.kohttp;


import com.lemon.library.kocore.utils.IOUtils;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KORequest {

    protected final HttpMethod method;
    protected final HttpUrl httpUrl;
    protected RequestParams params;
    protected byte[] body;
    protected Object tag;

    public KORequest(final KORequest source) {
        this.method = source.method;
        this.httpUrl = source.httpUrl;
        this.params = source.params;
        this.body = source.body;
        this.tag = source.tag;
//        this.listener = source.listener;
//        this.debug = source.debug;
    }

    public KORequest(final HttpMethod method, String url, Object tag) {
        this(method, url, new RequestParams(), tag);
    }

    public KORequest(final HttpMethod method, String url, final RequestParams params, Object tag) {
//        AssertUtils.notNull(method, "http method can not be null");
//        AssertUtils.notEmpty(url, "http url can not be null or empty");
//        AssertUtils.notNull(params, "http params can not be null");
        final HttpUrl hUrl = HttpUrl.parse(url);
//        AssertUtils.notNull(hUrl, "invalid url:" + url);
        this.method = method;
        this.httpUrl = HttpUrl.parse(url);
        this.params = new RequestParams(params);
        this.tag = tag;
    }


//    public KoRequest progress(final ProgressListener listener) {
//        this.listener = listener;
//        return this;
//    }

    public KORequest userAgent(final String userAgent) {
        return header(HttpConsts.USER_AGENT, userAgent);
    }

    public KORequest authorization(final String authorization) {
        return header(HttpConsts.AUTHORIZATION, authorization);
    }

    public KORequest referer(final String referer) {
        return header(HttpConsts.REFERER, referer);
    }

    public KORequest header(String name, String value) {
        this.params.header(name, value);
        return this;
    }

    public KORequest headers(Map<String, String> headers) {
        if (headers != null) {
            this.params.headers(headers);
        }
        return this;
    }

    public KORequest query(String key, String value) {
//        AssertUtils.notEmpty(key, "key must not be null or empty.");
        this.params.query(key, value);
        return this;
    }

    public KORequest queries(Map<String, String> queries) {
        this.params.queries(queries);
        return this;
    }

    protected void throwIfNotSupportBody() {
        if (!supportBody()) {
            throw new IllegalStateException("HTTP " + method.name() + " not support http body");
        }
    }

    public KORequest form(String key, String value) {
        if (supportBody()) {
            this.params.form(key, value);
        }
        return this;
    }

    public KORequest forms(Map<String, String> forms) {
        if (supportBody()) {
            this.params.forms(forms);
        }
        return this;
    }

    public KORequest parts(Collection<BodyPart> parts) {
        if (supportBody()) {
            for (final BodyPart part : parts) {
                part(part);
            }
        }
        return this;
    }

    public KORequest file(String key, File file) {
        if (supportBody()) {
            this.params.file(key, file);
        }
        return this;
    }

    public KORequest file(String key, File file, String contentType) {
        if (supportBody()) {
            this.params.file(key, file, contentType);
        }
        return this;
    }

    public KORequest file(String key, File file, String contentType, String fileName) {
        if (supportBody()) {
            this.params.file(key, file, contentType, fileName);
        }
        return this;
    }

    public KORequest file(String key, byte[] bytes) {
        if (supportBody()) {
            this.params.file(key, bytes);
        }
        return this;
    }

    public KORequest file(String key, byte[] bytes, String contentType) {
        if (supportBody()) {
            this.params.file(key, bytes, contentType);
        }
        return this;
    }

    public KORequest body(final byte[] body) {
        if (supportBody()) {
            this.body = body;
        }
        return this;
    }

    public KORequest body(final String content, final Charset charset) {
        if (supportBody()) {
            this.body = content.getBytes(charset);
        }
        return this;
    }

    public KORequest body(final File file) throws IOException {
        if (supportBody()) {
            this.body = IOUtils.readBytes(file);
        }
        return this;
    }

    public KORequest body(final Reader reader) throws IOException {
        if (supportBody()) {
            this.body = IOUtils.readBytes(reader);
        }
        return this;
    }

    public KORequest body(final InputStream stream) throws IOException {
        if (supportBody()) {
            this.body = IOUtils.readBytes(stream);
        }
        return this;
    }

    public KORequest params(final RequestParams params) {
        if (params != null) {
            queries(params.queries);
            if (supportBody()) {
                forms(params.forms);
                parts(params.parts);
            }
        }
        return this;
    }

    public HttpUrl url() {
        return buildUrlWithQueries();
    }

    public HttpMethod method() {
        return method;
    }

    public String originalUrl() {
        return httpUrl.toString();
    }

//    public ProgressListener listener() {
//        return listener;
//    }

    protected boolean supportBody() {
        return HttpMethod.supportBody(method);
    }

    protected KORequest part(final BodyPart part) {
        this.params.parts.add(part);
        return this;
    }

    protected KORequest removeHeader(String key) {
        this.params.headers.remove(key);
        return this;
    }

    protected KORequest removeQuery(String key) {
        this.params.queries.remove(key);
        return this;
    }

    protected KORequest removeForm(String key) {
        this.params.forms.remove(key);
        return this;
    }

    protected KORequest removePart(BodyPart part) {
        this.params.parts.remove(part);
        return this;
    }

    protected Object getTag() {
        return this.tag;
    }

    protected String getHeader(String key) {
        return this.params.getHeader(key);
    }

    protected String getQuery(String key) {
        return this.params.getQuery(key);
    }

    protected String getForm(String key) {
        return this.params.getForm(key);
    }

    protected BodyPart getPart(String key) {
        return this.params.getPart(key);
    }

    protected boolean hasHeader(String key) {
        return getHeader(key) != null;
    }

    protected boolean hasQuery(String key) {
        return getQuery(key) != null;
    }

    protected boolean hasForm(String key) {
        return getForm(key) != null;
    }

    protected boolean hasPart(String key) {
        return getPart(key) != null;
    }

    protected int queriesSize() {
        return queries().size();
    }

    protected int formsSize() {
        return form().size();
    }

    protected int headersSize() {
        return headers().size();
    }

    protected int partsSize() {
        return parts().size();
    }

    protected Map<String, String> headers() {
        return this.params.headers;
    }

    protected Map<String, String> queries() {
        return this.params.queries;
    }

    protected Map<String, String> form() {
        return this.params.forms;
    }

    protected List<BodyPart> parts() {
        return this.params.parts;
    }

    protected boolean hasParts() {
        return this.params.parts.size() > 0;
    }

    protected boolean hasForms() {
        return this.params.forms.size() > 0;
    }


    HttpUrl buildUrlWithQueries() {
        final HttpUrl.Builder builder = httpUrl.newBuilder();
        for (final Map.Entry<String, String> entry : params.queries().entrySet()) {
            builder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    protected void copy(final KORequest source) {
        this.params = source.params;
        this.body = source.body;
        this.tag = source.tag;
//        this.listener = source.listener;
//        this.debug = source.debug;
    }

    protected RequestBody getRequestBody()  {
        if (!supportBody()) {
            return null;
        }
        if (body != null) {
            return RequestBody.create(HttpConsts.MEDIA_TYPE_OCTET_STREAM, body);
        }
        RequestBody requestBody;
        if (hasParts()) {
            final MultipartBuilder multipart = new MultipartBuilder();
            for (final BodyPart part : parts()) {
                if (part.getBody() != null) {
                    multipart.addFormDataPart(part.getName(), part.getFileName(), part.getBody());
                }
            }
            for (Map.Entry<String, String> entry : form().entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                multipart.addFormDataPart(key, value == null ? "" : value);
            }
            requestBody = multipart.type(MultipartBuilder.FORM).build();
        } else if (hasForms()) {
            final FormEncodingBuilder bodyBuilder = new FormEncodingBuilder();
            for (Map.Entry<String, String> entry : form().entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                bodyBuilder.add(key, value == null ? "" : value);
            }
            requestBody = bodyBuilder.build();
        } else {
            //FIXME workaround for null body, waiting OkHttp release
            requestBody = RequestBody.create(null, HttpConsts.NO_BODY);
        }
        return requestBody;
    }

    @Override
    public String toString() {
        return "Request{HTTP " + method + " " + httpUrl + '}';
    }
}
