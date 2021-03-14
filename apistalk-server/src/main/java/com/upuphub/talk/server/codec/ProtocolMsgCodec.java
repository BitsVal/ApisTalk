package com.upuphub.talk.server.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolPackage;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-13 21:04
 **/
public class ProtocolMsgCodec implements MessageCodec<ProtocolPackage,ProtocolPackage> {
    /**
     * Protocol To Buffer
     */
    @Override
    public void encodeToWire(Buffer buffer, ProtocolPackage protocolPackage) {
        Buffer.buffer(protocolPackage.toByteArray());
    }

    /**
     * Buffer To Protocol
     */
    @Override
    public ProtocolPackage decodeFromWire(int pos, Buffer buffer) {
        try {
            return ProtocolPackage.parseFrom(buffer.getBytes());
        } catch (InvalidProtocolBufferException e) {
            return null;
        }
    }

    /**
     * 消息转换
     */
    @Override
    public ProtocolPackage transform(ProtocolPackage protocolPackage) {
        return protocolPackage;
    }

    @Override
    public String name() {
        return ProtocolMsgCodec.class.getName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }

    public static MessageCodec<ProtocolPackage,ProtocolPackage> create() {
        return new ProtocolMsgCodec();
    }
}
