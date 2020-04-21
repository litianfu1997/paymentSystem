package com.nnxy.syslog.annotation;

import java.lang.annotation.*;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-21 11:33
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Syslog {
    String value() default "";
}
