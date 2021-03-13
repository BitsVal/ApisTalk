package com.upuphub.talk.client.protocol_old;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

import java.io.*;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 23:23
 **/
public class ProtocolMsgCodec implements MessageCodec<ProtocolOld, ProtocolOld> {

    @Override
    public void encodeToWire(Buffer buffer, ProtocolOld protocolOld) {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        try (ObjectOutputStream o = new ObjectOutputStream(b)){
            o.writeObject(protocolOld);
            o.close();
            buffer.appendBytes(b.toByteArray());
        } catch (IOException e) { e.printStackTrace(); }

    }

    @Override
    public ProtocolOld decodeFromWire(int pos, Buffer buffer) {
        final ByteArrayInputStream b = new ByteArrayInputStream(buffer.getBytes());
        ProtocolOld protocolOld = null;
        try (ObjectInputStream o = new ObjectInputStream(b)){ protocolOld = (ProtocolOld) o.readObject();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return protocolOld;
    }

    @Override
    public ProtocolOld transform(ProtocolOld protocolOld) {
//        System.out.println("消息转换---");//可对接受消息进行转换,比如转换成另一个对象等
//        protocol.setName("姚振");
        return protocolOld;
    }

    @Override
    public String name() {
        return "protocolCodec";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }

    public static MessageCodec<ProtocolOld, ProtocolOld> create() {
        return new ProtocolMsgCodec();
    }
}
