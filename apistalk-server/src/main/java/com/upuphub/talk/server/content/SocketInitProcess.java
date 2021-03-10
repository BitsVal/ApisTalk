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
public final class SocketInitProcess {
    private static final Map<NetSocket, Long> TCP_INIT_SOCKETS = new ConcurrentHashMap<>();
    public static void putSocket(NetSocket netSocket,Long waitTime){
        TCP_INIT_SOCKETS.put(netSocket, System.currentTimeMillis() + waitTime);
    }

    public static void kickOutTheSocket(NetSocket netSocket){
        TCP_INIT_SOCKETS.remove(netSocket);
        netSocket.close();
    }

    public static Map<NetSocket, Long> getAll(){
        return TCP_INIT_SOCKETS;
    }

    public static void removeSocket(NetSocket netSocket) {
        TCP_INIT_SOCKETS.remove(netSocket);
    }
}
