package com.upuphub.talk.client.protocol;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

import java.io.*;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 23:23
 **/
public class ProtocolMsgCodec implements MessageCodec<Protocol,Protocol> {

    @Override
    public void encodeToWire(Buffer buffer, Protocol protocol) {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        try (ObjectOutputStream o = new ObjectOutputStream(b)){
            o.writeObject(protocol);
            o.close();
            buffer.appendBytes(b.toByteArray());
        } catch (IOException e) { e.printStackTrace(); }

    }

    @Override
    public Protocol decodeFromWire(int pos, Buffer buffer) {
        final ByteArrayInputStream b = new ByteArrayInputStream(buffer.getBytes());
        Protocol protocol = null;
        try (ObjectInputStream o = new ObjectInputStream(b)){ protocol = (Protocol) o.readObject();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return protocol;
    }

    @Override
    public Protocol transform(Protocol protocol) {
//        System.out.println("消息转换---");//可对接受消息进行转换,比如转换成另一个对象等
//        protocol.setName("姚振");
        return protocol;
    }

    @Override
    public String name() {
        return "protocolCodec";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }

    public static MessageCodec<Protocol,Protocol> create() {
        return new ProtocolMsgCodec();
    }
}
