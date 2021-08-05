package com.upuphub.apistalk.bean.annotation;

import java.lang.annotation.*;

/**
 * @author iWzl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {
    String[] value() default {};
}
