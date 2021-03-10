package com.upuphub.talk.server.content;

import io.vertx.core.net.NetSocket;

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
public final class SocketInitContent {
    private static final Map<NetSocket, Long> initSockets = new ConcurrentHashMap<>();
    public static void putSocket(NetSocket netSocket,Long waitTime){
        initSockets.put(netSocket, System.currentTimeMillis() + waitTime);
    }
    public static void removeSocket(NetSocket netSocket){
        initSockets.remove(netSocket);
        netSocket.close();
    }

    public static Map<NetSocket, Long> getAll(){
        return initSockets;
    }
}
