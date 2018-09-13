package com.moudle.iocutils.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.moudle.iocutils.annotation.ContentView;
import com.moudle.iocutils.annotation.EventBase;
import com.moudle.iocutils.annotation.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/8/28.
 */

public class ViewInjetUtils {

    private static final String TAG = "ViewInjetUtils";
    public static void inject(Object context){
        intjectLayout(context);
        injectView(context);
        injectClick(context);
    }

    private static void injectClick(final Object context) {
        Class<?> clazz = context.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations){
                Class<?> annntype = annotation.annotationType();
                EventBase eventBase = annntype.getAnnotation(EventBase.class);
                if(eventBase == null){
                    continue;
                }
                //获取方法名
                String listerName = eventBase.listenerSetter();
                //获取监听类型
                Class<?> listentype = eventBase.listenetType();
                //监听回调
                String callBackMethod = eventBase.callbackMethod();

                //动态代理  listener;
                Method vlaueMethod = null;
                try {
                    vlaueMethod = annntype.getDeclaredMethod("value");
                    try {
                        int[] viewId = (int[]) vlaueMethod.invoke(annotation);
                        for (int id : viewId){
                            Method findViewById = clazz.getMethod("findViewById",int.class);
                            View view = (View) findViewById.invoke(context,id);
                            if(view == null){
                                continue;
                            }
                 /*           view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText((Context) context,"-----------",Toast.LENGTH_SHORT).show();
                                }
                            });*/

                            //动态代理 --- 替换了方法，onLongClick  onClick 方法
                            //动态代理类似于转化
                            Method clickMethod = view.getClass().getMethod(listerName,listentype);
                            ListenerHandler handler = new ListenerHandler(method,context);
                            Log.d(TAG, "injectClick: " + listentype.getClassLoader());
                            Object proxy = Proxy.newProxyInstance(listentype.getClassLoader(),new Class[]{listentype},handler);
                            //view.setonClickListener(this) 代理了this
                            clickMethod.invoke(view,proxy);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectView(Object context) {
        Class<?> aClass = context.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields){
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if(viewInject != null){
                int value = viewInject.value();
                try {
                    Method method = aClass.getMethod("findViewById",int.class);
                    View invoke = (View) method.invoke(context, value);
                    field.setAccessible(true);
                    field.set(context,invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void intjectLayout(Object context) {
        int layoutId = 0;
        Class<?> clazz = context.getClass();
        ContentView annotation = clazz.getAnnotation(ContentView.class);
        if(annotation != null){
            layoutId = annotation.value();
            try {
                Method method = clazz.getMethod("setContentView",int.class);
                method.invoke(context,layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
