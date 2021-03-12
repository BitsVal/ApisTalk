package com.upuphub.talk.server.network.tcp;

import com.upuphub.talk.server.Environment;
import com.upuphub.talk.server.factory.ProtocalFactory;
import com.upuphub.talk.server.network.Gateway;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolType;
import com.upuphub.talk.server.utils.StringUtils;
import io.vertx.core.Promise;
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

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        before();
        vertx.createNetServer().connectHandler(socket->{
            socket.handler(event -> {
                logger.error(event.toString());
                Protocol protocol = null;
                try {
                    protocol = event.toJsonObject().mapTo(Protocol.class);
                }catch (Exception ignore){
                    logger.error("Protocol Error");
                    socket.write(ProtocalFactory.createProtocolErrorResponse(
                            socket.localAddress().host(),socket.remoteAddress().host()));
                    return;
                }
                if(null == protocol || null == protocol.getHeader()){
                    logger.error("Protocol Error");
                    socket.write(ProtocalFactory.createProtocolErrorResponse(
                            socket.localAddress().host(),socket.remoteAddress().host()));
                }else {
                    protocol.getHeader().setSocketId(socket.writeHandlerID());
                    Environment.registerSocket(socket);
                    String address = ProtocolType.C.getProtocolHandlerEventAddressByType(protocol.getHeader().getType());
                    if(StringUtils.isEmpty(address)){
                        logger.warn("protocol type not support");
                    }else {
                        vertx.eventBus().send(address,protocol);
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
    }

    @Override
    protected void before() {
        port = 8788;
    }
}
