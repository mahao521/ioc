package com.moudle.iocutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/8/28.
 */

@Retention(RetentionPolicy.RUNTIME) //运行时有效，一直有效
@Target(ElementType.TYPE)
public @interface ContentView {
    int value();
}
