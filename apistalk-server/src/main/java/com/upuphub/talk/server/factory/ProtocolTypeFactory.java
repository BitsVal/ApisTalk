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
    private static final Map<CMD,String> PROTOCOL_TYPE_MAPPER = new ConcurrentHashMap<>();

    static {
        PROTOCOL_TYPE_MAPPER.put(CMD.CMD_AUTHORIZATION_REQ,"apis.s.auth");
        PROTOCOL_TYPE_MAPPER.put(CMD.CMD_ECHO_REQ,"apis.s.echo");
        PROTOCOL_TYPE_MAPPER.put(CMD.CMD_HEARTBEAT_REQ,"apis.s.heartbeat");
        PROTOCOL_TYPE_MAPPER.put(CMD.CMD_QOS_RECEIVED_REQ,"apis.s.received");
        PROTOCOL_TYPE_MAPPER.put(CMD.CMD_INSTANT_MESSAGING_DATA_REQ,"apis.s.data");
    }

    public static String getEventAddressByCmd(CMD cmd){
        return PROTOCOL_TYPE_MAPPER.getOrDefault(cmd,UNKNOWN);
    }
}
