package com.upuphub.apistalk.bean.container;

import java.util.HashSet;
import java.util.Set;

/**
 * 类的容器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-08-05 21:57
 **/
public class ClassContainer {
    /**
     * CLASSES容器
     */
    public static final Set<Class<?>> CLASS_CONTAINER = new HashSet<>(1 << 6);

}
