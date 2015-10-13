package com.lemon.reader.base;

import com.lemon.library.kocore.eventbus.EventCenter;

public abstract class BaseFragment extends BaseLazyFragment {


    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {
    }
}
