package com.upuphub.talk.server.event;

import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.talk.server.protocol.Protocol;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-13 21:04
 **/
public class ProtocolMsgCodec implements MessageCodec<Protocol,Protocol> {
    /**
     * Protocol To Buffer
     */
    @Override
    public void encodeToWire(Buffer buffer, Protocol protocol) {
        Buffer.buffer(protocol.toByteArray());
    }

    /**
     * Buffer To Protocol
     */
    @Override
    public Protocol decodeFromWire(int pos, Buffer buffer) {
        try {
            return Protocol.parseFrom(buffer.getBytes());
        } catch (InvalidProtocolBufferException e) {
            return null;
        }
    }

    /**
     * 消息转换
     */
    @Override
    public Protocol transform(Protocol protocol) {
        return protocol;
    }

    @Override
    public String name() {
        return ProtocolMsgCodec.class.getName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }

    public static MessageCodec<Protocol,Protocol> create() {
        return new ProtocolMsgCodec();
    }
}
