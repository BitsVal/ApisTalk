package com.upuphub.talk.server.factory;

import com.google.protobuf.ByteString;
import com.upuphub.talk.server.config.ApisServerConfig;
import com.upuphub.talk.server.protocol.*;
import com.upuphub.talk.server.utils.NumberUtil;
import io.vertx.core.buffer.Buffer;

import java.util.UUID;

/**
 * 协议创建工厂
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 09:52
 **/
public final class ProtocolFactory {
    private static final String SERVER_VERSION =
            String.format("%s.%s",ApisServerConfig.APIS_SERVER_NAME,ApisServerConfig.APIS_SERVER_VERSION);

    public static Protocol buildAuthorizationRsp(String to,MESSAGE_CODE msgCode,String message){
       return Protocol.newBuilder()
               .setStatus(buildCommonProtocolStatus(msgCode,message))
               .setHeader(buildCommonProtocolHeader(CMD.CMD_AUTHORIZATION_RSP,to,UUID.randomUUID().toString(),QoS.QoS_AT_MOST_ONCE,0))
               .setData(AuthorizationRsp.newBuilder().build().toByteString())
               .build();
    }

    public static Status buildCommonProtocolStatus(MESSAGE_CODE msgCode,String message){
        return Status.newBuilder()
                .setCode(msgCode)
                .setMessage(message)
                .setTimestamp(System.currentTimeMillis())
                .setVersion(SERVER_VERSION)
                .build();
    }

    public static Header buildCommonProtocolHeader(CMD cmd,String to,String fingerPrint,QoS qos,Integer retryCount){
        return Header.newBuilder()
                .setCmd(cmd)
                .setFrom(ApisServerConfig.SERVER_PROTOCOL_TO)
                .setTo(to)
                .setFingerPrint(fingerPrint)
                .setRetryCount(retryCount)
                .setQos(qos)
                .build();
    }

    public static Buffer buildProtocolBuffer(Protocol protocol){
        byte[] bytes = protocol.toByteArray();
        return Buffer.buffer(NumberUtil.intToByte4(bytes.length)).appendBytes(bytes);
    }
}
