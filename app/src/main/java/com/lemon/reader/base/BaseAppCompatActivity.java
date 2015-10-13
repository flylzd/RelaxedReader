package com.lemon.reader.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lemon.library.kocore.eventbus.EventCenter;
import com.lemon.library.kocore.utils.StatusBarCompatUtils;
import com.lemon.reader.AppManager;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 作者：lemon
 * 日期：2015-09-28
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        if (isBindEventBus()) {
            EventBus.getDefault().register(this);
        }

        AppManager.getInstance().addActivity(this);

        beforeSetContentView();
        int layoutResID = getContentViewLayoutId();
        if (layoutResID != 0) {
            setContentView(layoutResID);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        ButterKnife.bind(this);
        afterSetContentView();

        if (hasStatusBarTint()) {
            int resID = getStatusBarTintResourceId();
            if (resID != 0) {
                StatusBarCompatUtils.setStatusBarTintResource(this, resID);
            } else {
                throw new IllegalArgumentException("You must return a right status bar tint resource Id");
            }
        }

        initView();
    }

    @Override
    public void finish() {
        super.finish();
        AppManager.getInstance().removeActivity(this);
//        if (toggleOverridePendingTransition()) {
//            switch (getOverridePendingTransitionMode()) {
//                case LEFT:
//                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
//                    break;
//                case RIGHT:
//                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
//                    break;
//                case TOP:
//                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
//                    break;
//                case BOTTOM:
//                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
//                    break;
//                case SCALE:
//                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
//                    break;
//                case FADE:
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    break;
//            }
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (isBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract void getBundleExtras(Bundle extras);

    protected abstract void beforeSetContentView();

    protected abstract void afterSetContentView();

    protected abstract int getContentViewLayoutId();

    protected abstract void initView();

    protected abstract boolean hasStatusBarTint();

    protected abstract int getStatusBarTintResourceId();

    protected abstract boolean isBindEventBus();

    protected abstract void onEventBusHandler(EventCenter eventCenter);

    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventBusHandler(eventCenter);
        }
    }

}
