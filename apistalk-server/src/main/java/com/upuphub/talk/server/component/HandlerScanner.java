package com.upuphub.talk.server.component;

import com.upuphub.talk.server.ServerLauncher;
import com.upuphub.talk.server.handler.AbstractProtocolHandler;
import com.upuphub.talk.server.utils.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-13 22:15
 **/
public class HandlerScanner {
    public static void main(String[] args) {
        String[] packages = {ServerLauncher.class.getPackage().getName(),""};
        Set<Class<? extends AbstractProtocolHandler>> handlerSet = new HashSet<>();
        for (Class<? extends AbstractProtocolHandler> subClass : ReflectionUtils.getSubClass(packages, AbstractProtocolHandler.class)) {
            if(!Modifier.isAbstract(subClass.getModifiers())){
                handlerSet.add(subClass);
            }
        }
        System.out.println();
    }
}
