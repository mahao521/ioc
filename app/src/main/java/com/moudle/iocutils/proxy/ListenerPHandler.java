package com.moudle.iocutils.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/9/13.
 */

public class ListenerPHandler implements InvocationHandler {

    private static final String TAG = "ListenerPHandler";
    private Object mObject;

    public ListenerPHandler(Object object){
        this.mObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Log.d(TAG, "invoke: "+ null + method.getName());
/*        if(method.getName().equals("sing")){
            return  method.invoke(mObject,args);
        }*/
        return  method.invoke(mObject,args);
    }
}