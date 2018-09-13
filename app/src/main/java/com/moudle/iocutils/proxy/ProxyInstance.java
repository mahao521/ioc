package com.moudle.iocutils.proxy;

import android.util.Log;
import android.view.View;

import com.moudle.iocutils.util.ListenerHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/9/13.
 */

public class ProxyInstance {

    private static final String TAG = "ProxyInstance";

    public void getProxy(){
        // 我们要代理的真实对象
        UpdateInfoListener realSubject = new UpdateInfoListenerImpl();
        // 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        ListenerPHandler handler = new ListenerPHandler(realSubject);
        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        Log.d(TAG, "onCreate: " + ".." + realSubject.getClass().getClassLoader());
        UpdateInfoListener mSubject = (UpdateInfoListener) java.lang.reflect.Proxy.newProxyInstance(UpdateInfoListener.class.getClassLoader(),
                new Class[]{View.OnClickListener.class,UpdateInfoListener.class}, handler);
        Log.d(TAG, "onCreate: " + mSubject);
        String mahao = mSubject.sing("mahao");
        Log.d(TAG, "test: " + mahao);
    }

    /**
     *   目标： 代理onCLickListener
     *   只要谁调用了 setonClickListenr（） 就会走这里； ---------------替换过程。。
     *   真正的对象 不关心。。
     * @param object
     */
    public void  registerProxyClick(final Object object, View view){

        try {
            Method method = view.getClass().getMethod("setOnClickListener",View.OnClickListener.class);
            final Method realClick = object.getClass().getMethod("realClick", View.class);
            Object proxy = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(),
                    new Class[]{View.OnClickListener.class},
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            return realClick.invoke(object,args);
                        }
                    });
            method.invoke(view,proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
