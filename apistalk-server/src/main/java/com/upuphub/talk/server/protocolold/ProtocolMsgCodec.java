package com.upuphub.talk.server.protocolold;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

import java.io.*;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 23:23
 **/
public class ProtocolMsgCodec implements MessageCodec<Protocol_OLD, Protocol_OLD> {

    @Override
    public void encodeToWire(Buffer buffer, Protocol_OLD protocolOLD) {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        try (ObjectOutputStream o = new ObjectOutputStream(b)){
            o.writeObject(protocolOLD);
            o.close();
            buffer.appendBytes(b.toByteArray());
        } catch (IOException e) { e.printStackTrace(); }

    }

    @Override
    public Protocol_OLD decodeFromWire(int pos, Buffer buffer) {
        final ByteArrayInputStream b = new ByteArrayInputStream(buffer.getBytes());
        Protocol_OLD protocolOLD = null;
        try (ObjectInputStream o = new ObjectInputStream(b)){ protocolOLD = (Protocol_OLD) o.readObject();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return protocolOLD;
    }

    @Override
    public Protocol_OLD transform(Protocol_OLD protocolOLD) {
//        System.out.println("消息转换---");//可对接受消息进行转换,比如转换成另一个对象等
//        protocol.setName("姚振");
        return protocolOLD;
    }

    @Override
    public String name() {
        return "protocolCodec";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }

    public static MessageCodec<Protocol_OLD, Protocol_OLD> create() {
        return new ProtocolMsgCodec();
    }
}
