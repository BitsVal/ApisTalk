package com.upuphub.talk.server.annotation;

import com.upuphub.talk.server.handler.HandlerType;

import java.lang.annotation.*;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-15 16:16
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface ApisHandler {
    /**
     * @return 处理类型
     */
    HandlerType value();

    /**
     * @return 创建的处理的实例数量
     */
    int instances() default 1;
}
