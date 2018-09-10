package com.moudle.iocutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/8/28.
 */

@Retention(RetentionPolicy.RUNTIME)
//注解在另一个注解
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {

    //方法名
    String listenerSetter();

    //事件类型
    Class<?> listenetType();

    //事件触发回调
    String callbackMethod();
}
