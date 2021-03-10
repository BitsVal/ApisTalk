package com.upuphub.talk.server.content;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public final class SocketOnlineProcess {
    private static final Map<String,String> P2S_ONLINE_SOCKETS = new ConcurrentHashMap<>();
    private static final Map<String,String> S2P_ONLINE_SOCKETS = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(SocketOnlineProcess.class);

    public static void putSocket(String to,String socketId){
        synchronized (SocketOnlineProcess.class){
            if(P2S_ONLINE_SOCKETS.containsKey(to)){
                String socketIdBak = P2S_ONLINE_SOCKETS.get(to);
                if(socketIdBak.equals(socketId)){
                    logger.warn("重复注册Socket链接 {} {}",to,socketId);
                }else {
                    logger.warn("刷新Socket链接、这里可能需要考虑是否踢掉原来的链接 {} {} {}",to,socketId,socketIdBak);
                }
            }
            P2S_ONLINE_SOCKETS.put(to, socketId);
            S2P_ONLINE_SOCKETS.put(socketId,to);
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
}
