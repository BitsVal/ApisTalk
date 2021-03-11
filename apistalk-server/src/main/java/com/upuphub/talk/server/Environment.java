package com.upuphub.talk.server;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务环境
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 21:43
 **/
public final class Environment {
    private static final Logger logger = LoggerFactory.getLogger(Environment.class);
    private static final transient Map<NetSocket, Long> TCP_INIT_SOCKETS = new ConcurrentHashMap<>();
    private static final transient Map<String,String> P2S_ONLINE_SOCKETS = new ConcurrentHashMap<>();
    private static final transient Map<String,String> S2P_ONLINE_SOCKETS = new ConcurrentHashMap<>();

    private static Environment instance = null;
    private static Vertx vertx;
    private String config;

    public Environment(Vertx vertx) {
        Environment.vertx = vertx;
    }

    public static Vertx getVertx() {
        return Environment.vertx;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public static void putSocket(String to,NetSocket netSocket){
        synchronized (Environment.class){
            String handlerId = netSocket.writeHandlerID();
            if(P2S_ONLINE_SOCKETS.containsKey(to)){
                String socketIdBak = P2S_ONLINE_SOCKETS.get(to);
                if(socketIdBak.equals(netSocket.writeHandlerID())){
                    logger.warn("重复注册Socket链接 {} {}",to,handlerId);
                }else {
                    logger.warn("刷新Socket链接、这里可能需要考虑是否踢掉原来的链接 {} {} {}",to,handlerId,socketIdBak);
                }
            }
            P2S_ONLINE_SOCKETS.put(to, handlerId);
            S2P_ONLINE_SOCKETS.put(handlerId,to);
        }

    }

    public static void removeSocketByTo(String to){
        if(P2S_ONLINE_SOCKETS.containsKey(to)){
            String socketId = P2S_ONLINE_SOCKETS.get(to);
            P2S_ONLINE_SOCKETS.remove(to);
            S2P_ONLINE_SOCKETS.remove(socketId);
        }
    }

    public static Set<String> getAllSocketIdSet(){
        return new HashSet<>(P2S_ONLINE_SOCKETS.values());
    }

    public static boolean isRegistered(String from) {
        return P2S_ONLINE_SOCKETS.containsKey(from);
    }

    public static Map<NetSocket, Long> getAllInitSocketsAndExpireTime(){
        return TCP_INIT_SOCKETS;
    }

    public static void removeSocketFromInitContainer(NetSocket netSocket) {
        TCP_INIT_SOCKETS.remove(netSocket);
    }
}
