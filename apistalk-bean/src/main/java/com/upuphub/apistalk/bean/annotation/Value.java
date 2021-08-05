package com.upuphub.apistalk.bean.annotation;

import java.lang.annotation.*;

/**
 * @author iWzl
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value() default "";
}
