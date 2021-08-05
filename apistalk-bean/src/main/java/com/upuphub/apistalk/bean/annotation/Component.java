package com.upuphub.apistalk.bean.annotation;

import java.lang.annotation.*;

/**
 * @author iWzl
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String name() default "";
}