package com.upuphub.talk.server.factory;

import com.upuphub.talk.server.protocol.CMD;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-13 21:27
 **/
public class ProtocolTypeFactory {
    private static final String UNKNOWN = "apis.s.unknown";
    public static final String BOSS_EVENT_ADDRESS = "apis.s.boss";
    private static final Map<CMD,String> PROTOCOL_TYPE_MAPPER = new ConcurrentHashMap<>();

    static {
        PROTOCOL_TYPE_MAPPER.put(CMD.CMD_AUTHORIZATION_REQ,"apis.s.auth");
    }

    public static String getEventAddressByCmd(CMD cmd){
        return PROTOCOL_TYPE_MAPPER.getOrDefault(cmd,UNKNOWN);
    }
}
