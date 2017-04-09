package com.main.zk.cashbook.util;

import android.util.Log;

/**
 * Created by Administrator on 2017/2/6 0006.
 */

public class LogUtil {

    /**
     * LOG为true时，输出日志；否则，不输出
     */
    private static final boolean LOG = true;

    public static void i(String tag, String string) {
        if (LOG) Log.i(tag, string);
    }

    public static void e(String tag, String string) {
        if (LOG) Log.e(tag, string);
    }

    public static void d(String tag, String string) {
        if (LOG) Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (LOG) Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (LOG) Log.w(tag, string);
    }

}
