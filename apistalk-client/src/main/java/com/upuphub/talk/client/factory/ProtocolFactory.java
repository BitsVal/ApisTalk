package com.upuphub.talk.client.factory;

import com.google.protobuf.ByteString;
import com.upuphub.talk.client.protocol.CMD;
import com.upuphub.talk.client.protocol.Header;
import com.upuphub.talk.client.protocol.Protocol;
import com.upuphub.talk.client.protocol.QoS;
import com.upuphub.talk.client.util.NumberUtil;
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

    public static Buffer buildAuthorizationReq(String from,String to,String value){
        Protocol protocol = Protocol.newBuilder()
                .setHeader(Header.newBuilder()
                        .setCmd(CMD.CMD_AUTHORIZATION_REQ)
                        .setFrom(from)
                        .setTo(to)
                        .setFingerPrint(UUID.randomUUID().toString())
                        .setRetryCount(0)
                        .setQos(QoS.QoS_AT_MOST_ONCE)
                        .build())
                .setData(ByteString.copyFrom(value, Charset.defaultCharset()))
                .build();
        byte[] bytes = protocol.toByteArray();
        return Buffer.buffer(NumberUtil.intToByte4(bytes.length)).appendBytes(bytes);
    }
}
