package com.upuphub.talk.example.client;

import com.upuphub.talk.client.ClientLauncher;
import com.upuphub.talk.client.network.tcp.TcpApisClient;
import com.upuphub.talk.client.process.ReceivedProcess;
import com.upuphub.talk.client.protocol_old.ProtocolOld;
import com.upuphub.talk.client.protocol_old.ProtocolMsgCodec;
import io.vertx.core.Vertx;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 09:46
 **/
public class ApisTalkClientLauncher extends ClientLauncher {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.eventBus().registerDefaultCodec(ProtocolOld.class, ProtocolMsgCodec.create());
        vertx.deployVerticle(TcpApisClient.class.getName());
        vertx.deployVerticle(ReceivedProcess.class.getName());
    }
}
