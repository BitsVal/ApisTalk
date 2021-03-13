package com.upuphub.talk.server.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upuphub.talk.server.protocolold.Protocol_OLD;
import com.upuphub.talk.server.protocolold.ProtocolType;
import io.vertx.core.buffer.Buffer;

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
            Protocol_OLD protocolOLD = new Protocol_OLD();
            Protocol_OLD.Header header = new Protocol_OLD.Header();
            header.setFrom(from);
            header.setTo(to);
            header.setDataType("Json");
            header.setRc(0);
            header.setType(ProtocolType.S.FROM_SERVER_TYPE_OF_RESPONSE$FOR$ERROR);
            header.setFp(UUID.randomUUID().toString());
            header.setQoS((byte) 0);
            protocolOLD.setHeader(header);
            protocolOLD.setData("Error Protocol");
            return Buffer.buffer(objectMapper.writeValueAsString(protocolOLD));
        }catch (JsonProcessingException ex){
            return Buffer.buffer("");
        }

    }
}
