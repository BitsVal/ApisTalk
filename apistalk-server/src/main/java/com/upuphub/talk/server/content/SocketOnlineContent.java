package com.upuphub.talk.server.content;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务在线上下文
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 15:41
 **/
public final class SocketOnlineContent {
    private static final Map<String,String> onlineSockets = new ConcurrentHashMap<>();

    public static void putSocket(String to,String socketId){
        onlineSockets.put(to, socketId);
    }

    public static void removeSocketByTo(String to){
        onlineSockets.remove(to);
    }

    public static Set<String> getAllSocketIdSet(){
       return new HashSet<>(onlineSockets.values());
    }
}
