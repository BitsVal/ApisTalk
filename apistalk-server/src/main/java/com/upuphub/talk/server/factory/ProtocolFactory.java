package com.upuphub.talk.server.factory;

import com.google.protobuf.ByteString;
import com.upuphub.talk.server.config.ApisServerConfig;
import com.upuphub.talk.server.protocol.*;
import com.upuphub.talk.server.utils.NumberUtil;
import io.vertx.core.buffer.Buffer;

import java.nio.charset.Charset;
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

    public static Protocol buildAuthorizationRsp(String to){
       return Protocol.newBuilder()
                .setHeader(Header.newBuilder()
                        .setCmd(CMD.CMD_AUTHORIZATION_RSP)
                        .setFrom(SERVER_VERSION)
                        .setTo(to)
                        .setFingerPrint(UUID.randomUUID().toString())
                        .setRetryCount(0)
                        .setQos(QoS.QoS_AT_MOST_ONCE)
                        .build())
                .setData(AuthorizationRsp.newBuilder().build().toByteString())
                .build();
    }

    public static Buffer buildProtocolBuffer(Protocol protocol){
        byte[] bytes = protocol.toByteArray();
        return Buffer.buffer(NumberUtil.intToByte4(bytes.length)).appendBytes(bytes);
    }


//    public static Buffer buildAuthorizationReq(String from, String to, String value){
//        Protocol protocol = Protocol.newBuilder()
//                .setHeader(Header.newBuilder()
//                        .setCmd(CMD.CMD_AUTHORIZATION_RSP)
//                        .setFrom(from)
//                        .setTo(to)
//                        .setFingerPrint(UUID.randomUUID().toString())
//                        .setRetryCount(0)
//                        .setQos(QoS.AT_MOST_ONCE)
//                        .setVersion(SERVER_VERSION)
//                        .build())
//                .setData(ByteString.copyFrom(value, Charset.defaultCharset()))
//                .build();
//        byte[] bytes = protocol.toByteArray();
//        return Buffer.buffer(NumberUtil.intToByte4(bytes.length)).appendBytes(bytes);
//    }
}
