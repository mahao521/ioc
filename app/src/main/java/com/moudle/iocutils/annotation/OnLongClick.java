package com.moudle.iocutils.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/8/28.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnLongClickListener",listenetType = View.OnLongClickListener.class,callbackMethod = "onLongClick")
public @interface OnLongClick {
    int[] value() default -1;
}
