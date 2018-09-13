package com.moudle.iocutils.util;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/8/28.
 */

public class ListenerHandler implements InvocationHandler {

    private static final String TAG = "ListenerHandler";
    private Method mMethod;
    private Object mObject;

    //传入真正的代理对象
    public ListenerHandler(Method method,Object object){
        this.mMethod = method;
        this.mObject = object;
    }

    //代理替换了方法，，，，转移了对象。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(TAG, "invoke: " + Arrays.toString(args) + method.getName());

        //这里调用-----可以是从其他app中获取的内容，，代理对象，不一定有真实的mObject
        //调用代理类中的方法。

       // return mMethod.invoke(mObject,args);
        return null;
    }
}
