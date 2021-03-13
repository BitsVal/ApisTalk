package com.upuphub.talk.client.network.tcp;

import com.upuphub.talk.client.factory.ProtocolFactory;
import com.upuphub.talk.client.network.ApisClient;
import com.upuphub.talk.client.network.ProtocolLengthRecordParser;
import io.vertx.core.Promise;
import io.vertx.core.net.NetSocket;

/**
 * Tcp客户端实现
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-11 18:21
 **/
public class TcpApisClient extends ApisClient {
    private String id;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        before();
        vertx.createNetClient().connect(port, host, res->{
            if(res.succeeded()){
                NetSocket socket = res.result();
                id = socket.writeHandlerID();
                res.result().handler(ProtocolLengthRecordParser.newProtocolParser(socket, vertx.eventBus()));
            }
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        vertx.setPeriodic(1,h->{
            vertx.eventBus().send(id, ProtocolFactory.buildAuthorizationReq("Client","Server","Token"));
        });
        super.start(startPromise);
    }

    @Override
    protected void before() {
        port = 8788;
        host = "localhost";
    }
}
