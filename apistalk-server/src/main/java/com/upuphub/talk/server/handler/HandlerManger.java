package com.upuphub.talk.server.handler;

import com.upuphub.talk.server.annotation.ApisHandler;
import com.upuphub.talk.server.utils.ReflectionUtils;

import java.util.*;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-14 10:54
 **/
public final class HandlerManger {
    private final static Map<HandlerType,Class<?>> APIS_HANDLER = new HashMap<>();
    private final static Map<Class<?>,Integer> INSTANCES_CONFIG = new HashMap<>();

    public static boolean hasHandler(HandlerType handlerType){
        return APIS_HANDLER.containsKey(handlerType);
    }

    public static void initProtocolHandler(Class<?> clazz) {
        Set<Class<?>> protocolHandlerSet =
                ReflectionUtils.scanAnnotatedClass(new String[]{clazz.getPackage().getName()}, ApisHandler.class);
        for (Class<?> handler : protocolHandlerSet) {
            if (handler.isAnnotationPresent(ApisHandler.class)) {
                ApisHandler apisHandler = handler.getDeclaredAnnotation(ApisHandler.class);
                if (handler.getSuperclass() == AbstractProtocolHandler.class) {
                    APIS_HANDLER.put(apisHandler.value(), handler);
                    INSTANCES_CONFIG.put(handler, apisHandler.instances());
                }
            }
        }
    }


    public static  Map<Class<?>,Integer> getServerApisHandlers(){
        return INSTANCES_CONFIG;
    }
}
