package com.lemon.reader;


public class AppConstants {

    public static final int EVENT_BEGIN = 0X100;
    public static final int EVENT_REFRESH_DATA = EVENT_BEGIN + 10;
    public static final int EVENT_LOAD_MORE_DATA = EVENT_BEGIN + 20;
    public static final int EVENT_START_PLAY_MUSIC = EVENT_BEGIN + 30;
    public static final int EVENT_STOP_PLAY_MUSIC = EVENT_BEGIN + 40;

    public static final class EventBusCode{
        public static final int EVENT_CODE_IMAGE_DETAIL = 100;
    }
}
