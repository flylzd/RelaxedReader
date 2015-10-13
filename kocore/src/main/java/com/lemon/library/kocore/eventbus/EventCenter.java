package com.lemon.library.kocore.eventbus;


import android.view.View;

public class EventCenter<T> {

    /**
     * reserved data
     */
    private T data;

    /**
     * this code distinguish between different events
     */
    private int eventCode = -1;

    private View view;

    public EventCenter(int eventCode) {
        this(eventCode, null);
    }

    public EventCenter(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public EventCenter(int eventCode, T data,View view) {
        this.eventCode = eventCode;
        this.data = data;
        this.view = view;
    }

    /**
     * get event code
     *
     * @return
     */
    public int getEventCode() {
        return this.eventCode;
    }

    /**
     * get event reserved data
     *
     * @return
     */
    public T getData() {
        return this.data;
    }

    public View getView() {
        return  this.view;
    }
}
