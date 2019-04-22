package com.tyh.gittest.viewpage.utils;

import android.util.Log;

import com.tyh.gittest.BuildConfig;

/**
 * Created by Tan Yan Hao on 2019/4/19.
 */
public class LogUtil {

    private static final String TAG = "LOG";

    private static final boolean isDebug = BuildConfig.DEBUG;

    public static void d(String msg, Object... args) {
        if (!isDebug) {
            return;
        }
        Log.d(TAG, String.format(msg, args));
    }
}
