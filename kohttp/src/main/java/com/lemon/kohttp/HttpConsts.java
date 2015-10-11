package com.lemon.kohttp;

import com.lemon.library.kocore.Charsets;
import com.squareup.okhttp.MediaType;

import java.nio.charset.Charset;

/**
 * 作者：lemon
 * 日期：2015-09-14
 */
public class HttpConsts {

    public static String ENCODING_UTF8 = Charsets.ENCODING_UTF_8;
    public static Charset CHARSET_UTF8 = Charsets.UTF_8;

    public static int BUFFER_SIZE = 10 * 1024;

    public static int CONNECT_TIMEOUT = 10 * 1000;
    public static int READ_TIMEOUT = 10 * 1000;
    public static int WRITE_TIMEOUT = 10 * 1000;

    public static String APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static MediaType MEDIA_TYPE_OCTET_STREAM = MediaType.parse("application/octet-stream; charset=utf-8");
    public static String CONTENT_TYPE_FORM_ENCODED = "application/x-www-form-urlencoded";
    public static String EMPTY_STRING = "";
    public static String DEFAULT_NAME = "nofilename";

    public static final byte[] NO_BODY = new byte[0];

    public static final char QUERY_STRING_SEPARATOR = '?';
    public static final String PARAM_SEPARATOR = "&";
    public static final String PAIR_SEPARATOR = "=";


    /**
     * HEADERS
     */
    public static final String HOST = "Host";
    public static final String REFERER = "Referer";
    public static final String ENCODING_GZIP = "gzip";
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LOCATION = "Location";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String USER_AGENT = "User-Agent";
}
