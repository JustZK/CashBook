package com.main.zk.cashbook.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ZK on 2017/3/31.
 */

public class ToastUtil {

    private static final boolean TOAST = true;

    public static void makeText(Context context, String string) {
        if (TOAST) Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
