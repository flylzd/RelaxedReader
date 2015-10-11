package com.lemon.kohttp;



public interface RequestInterceptor {
    void intercept(final KORequest request);
}