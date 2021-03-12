package com.upuphub.talk.server.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolType;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
 * 协议创建工厂
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 09:52
 **/
public final class ProtocalFactory {
    private  static final ObjectMapper objectMapper = new ObjectMapper();

    public static Buffer createProtocolErrorResponse(String from,String to){
        try {
            Protocol protocol = new Protocol();
            Protocol.Header header = new Protocol.Header();
            header.setFrom(from);
            header.setTo(to);
            header.setDataType("Json");
            header.setRc(0);
            header.setType(ProtocolType.S.FROM_SERVER_TYPE_OF_RESPONSE$FOR$ERROR);
            header.setFp(UUID.randomUUID().toString());
            header.setQoS((byte) 0);
            protocol.setHeader(header);
            protocol.setData("Error Protocol");
            return Buffer.buffer(objectMapper.writeValueAsString(protocol));
        }catch (JsonProcessingException ex){
            return Buffer.buffer("");
        }

    }
}
