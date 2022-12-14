package com.wh.file.utils.annotation;

import java.lang.annotation.*;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 10:53 2022/2/16
 */
@Target(ElementType.METHOD) // 作用于方法，不包含构造方法
@Retention(RetentionPolicy.RUNTIME) // JVM会读取注解，同时会保存到class文件中
@Documented // 如果一种声明使用Documented进行注解，这种类型的注解被作为被标注的程序成员的公共AP
public @interface SysLogs {
    String title() default "";
}
