package com.moudle.iocutils.proxy;

import android.util.Log;

/**
 * Created by Administrator on 2018/9/13.
 */
public class UpdateInfoListenerImpl implements UpdateInfoListener{

    private static final String TAG = "UpdateInfoListenerImpl";
    private String name;


    @Override
    public String sing(String name) {
        return "亲爱的，那并不是爱情";
    }

    @Override
    public String dance(String name) {
        return "芭蕾舞";
    }
}