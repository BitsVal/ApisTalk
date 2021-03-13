package com.upuphub.talk.example.server;

import com.upuphub.talk.server.ServerLauncher;
import com.upuphub.talk.server.handler.AbstractProtocolHandler;
import com.upuphub.talk.server.utils.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 09:44
 **/
public class ApisTalkServerLauncher extends ServerLauncher {
//    public static void main(String[] args) throws Exception {
//        new ApisTalkServerLauncher().startup();
//    }

    public static void main(String[] args) {
        String[] packages = {ApisTalkServerLauncher.class.getPackage().getName(),""};
        Set<Class<? extends AbstractProtocolHandler>> handlerSet = new HashSet<>();
        for (Class<? extends AbstractProtocolHandler> subClass : ReflectionUtils.getSubClass(packages, AbstractProtocolHandler.class)) {
            if(!Modifier.isAbstract(subClass.getModifiers())){
                handlerSet.add(subClass);
            }
        }
        System.out.println();
    }
}
