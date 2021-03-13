package com.upuphub.talk.client.network.tcp;

import com.upuphub.talk.client.network.ApisClient;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
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
                res.result().handler(event -> {
                    System.out.println(event.toString());

//                    Protocol protocol = event.toJsonObject().mapTo(Protocol.class);
//                    if(null == protocol || null == protocol.getHeader()){
//
//                    }else{
//                        switch (protocol.getHeader().getType()){
//                            case ProtocolType.S.FROM_SERVER_TYPE_OF_RECEIVED:
//                                vertx.eventBus().send(
//                                        ProtocolType.S.FROM_SERVER_TYPE_OF_RECEIVED_EVENT_ADDRESS,protocol);
//                                break;
//                            case ProtocolType.S.FROM_SERVER_TYPE_OF_COMMON$DATA:
//                                break;
//                            default:
//                                break;
//                        }
//                    }
                });
            }
        });
        vertx.setPeriodic(1000,h->{
            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
//        vertx.setPeriodic(1,h->{
//            vertx.eventBus().send(id, Buffer.buffer("\"header\":{\"from\":\"demoData\",\"to\":\"demoData\",\"type\":0,\"QoS\":1,\"rc\":1,\"fp\":\"demoData\",\"dataType\":\"demoData\"},\"data\":\"token\"}"));
//        });
        super.start(startPromise);
    }

    @Override
    protected void before() {
        port = 8788;
        host = "localhost";
    }
}
