package com.upuphub.apistalk.bean.container;

import com.upuphub.apistalk.bean.common.BeanHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean全局容器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-08-05 21:20
 **/
public final class BeanContainer {
    /**
     * Bean实例容器
     */
    private static final Map<String,Object> BEANS_CONTAINER = new ConcurrentHashMap<>(1 << 6 );

    public static <T>T getBeanInstanceByClazz(Class<T> clazz){
        return (T) BEANS_CONTAINER.get(BeanHelper.getBeanName(clazz));
    }


}
