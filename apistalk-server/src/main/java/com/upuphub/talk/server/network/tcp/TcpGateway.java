package com.upuphub.talk.server.network.tcp;

import com.upuphub.talk.server.Environment;
import com.upuphub.talk.server.network.Gateway;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolType;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TcpGetaway
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 11:43
 **/
public class TcpGateway extends Gateway {
    Logger logger = LoggerFactory.getLogger(TcpGateway.class);
    String id = "";

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        before();
        vertx.createNetServer().connectHandler(socket->{
            id = socket.writeHandlerID();
            socket.handler(event -> {
                Protocol protocol = event.toJsonObject().mapTo(Protocol.class);
                if(null == protocol || null == protocol.getHeader()){
                    logger.error("Protocol Error");
                }else {
                    protocol.getHeader().setSocketId(socket.writeHandlerID());
                    Environment.registerSocket(socket);
                    switch (protocol.getHeader().getType()){
                        // todo 这里可以优化成有根据Type执行映射成对应对象并发送
                        case ProtocolType.C.FROM_CLIENT_TYPE_OF_REGISTER:
                            vertx.eventBus().send(ProtocolType.C.FROM_CLIENT_TYPE_OF_REGISTER_EVENT_ADDRESS,protocol);
                            break;
                        case ProtocolType.C.FROM_CLIENT_TYPE_OF_KEEP$ALIVE:
                            logger.error("Protocol Error");
                            break;
                        case ProtocolType.C.FROM_CLIENT_TYPE_OF_COMMON$DATA:
                            logger.error("Protocol Error");
                            break;
                        case ProtocolType.C.FROM_CLIENT_TYPE_OF_ECHO:
                            logger.error("Protocol Error");
                            break;
                        case ProtocolType.C.FROM_CLIENT_TYPE_OF_RECEIVED:
                            logger.error("Protocol Error");
                            break;
                        default:
                            break;
                    }
                }
            });
            // 监听客户端的退出连接
            socket.closeHandler(close -> logger.info("客户端退出 - [{}]",socket.writeHandlerID()));
        }).listen(port,host, net -> {
            if (net.succeeded()) {
                startPromise.complete();
                logger.info("TcpGateway初始化成功,Host: {},Port: {}",host,port);
            }
        });
        // 这里清理建立了Tcp连接但是不作为的
//        vertx.setPeriodic(2000,event -> {
//            vertx.eventBus().publish(id, Buffer.buffer("hello"));
//        });
    }

    @Override
    protected void before() {
        port = 8788;
    }
}
