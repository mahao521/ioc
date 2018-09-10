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
@EventBase(listenerSetter = "setOnClickListener",listenetType = View.OnClickListener.class,callbackMethod = "onClick")
public @interface OnClick {
    int[] value() default -1;
}
