package com.lemon.library.kocore.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * 作者：lemon
 * 日期：2015-09-28
 */
public class StatusBarCompatUtils {

    public static void setStatusBarTintResource(Activity activity, int res) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity.getWindow(), true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);

        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(res);//通知栏所需颜色
    }


    @TargetApi(19)
    public static void setTranslucentStatus(Window win, boolean on) {
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
