package com.upuphub.talk.server;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务环境
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 21:43
 **/
public final class Environment {
    private static final Logger logger = LoggerFactory.getLogger(Environment.class);
    private static final transient Map<String, NetSocket> CONNECTED_TCP_SOCKETS = new ConcurrentHashMap<>();
    private static final transient Map<String, Set<String>> P2S_ONLINE_SOCKETS = new ConcurrentHashMap<>();
    private static final transient Map<String,String> S2P_ONLINE_SOCKETS = new ConcurrentHashMap<>();

    private static Environment instance = null;
    private static Vertx vertx;

    public Environment(Vertx vertx) {
        Environment.vertx = vertx;
    }

    public static Vertx getVertx() {
        return Environment.vertx;
    }

    public static void registerSocket(NetSocket socket) {
        String handlerId = socket.writeHandlerID();
        if(!CONNECTED_TCP_SOCKETS.containsKey(socket.writeHandlerID())){
            CONNECTED_TCP_SOCKETS.put(handlerId,socket);
        }
    }

    public static boolean isRegistered(String from) {
        return P2S_ONLINE_SOCKETS.containsKey(from);
    }

    public static void registerConnectionSession(String from, String socketId) {
        synchronized (CONNECTED_TCP_SOCKETS){
            if(CONNECTED_TCP_SOCKETS.containsKey(socketId)){
                if(P2S_ONLINE_SOCKETS.containsKey(from)){
                    if(P2S_ONLINE_SOCKETS.get(from).contains(socketId)){
                        // todo 这个可能用户多端登陆,这里可以处理用户多端登陆的问题
                        P2S_ONLINE_SOCKETS.get(from).add(socketId);
                    }else {
                        logger.warn("这里用户可能建立多个链接?,断开链接重试?");
                    }
                }else {
                    Set<String> sockets = new HashSet<>();
                    sockets.add(socketId);
                    P2S_ONLINE_SOCKETS.put(from,sockets);
                    S2P_ONLINE_SOCKETS.put(socketId,from);
                }
            }else {
                throw new RuntimeException();
            }
        }
    }

    public static void pickOutSocketBySocketId(String socketId) {
        synchronized (CONNECTED_TCP_SOCKETS){
            if(CONNECTED_TCP_SOCKETS.containsKey(socketId)){
                CONNECTED_TCP_SOCKETS.get(socketId).close();
                CONNECTED_TCP_SOCKETS.remove(socketId);
            }
        }
    }
}
