package com.upuphub.talk.server.network.tcp;

import com.upuphub.talk.server.network.ProtocolLengthRecordParser;
import com.upuphub.talk.server.network.Gateway;
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

            socket.handler(ProtocolLengthRecordParser.newProtocolParser(socket,vertx.eventBus()));//event -> {
//                logger.error(event.toString());
//                Protocol_OLD protocolOLD = null;
//                try {
//                    protocolOLD = event.toJsonObject().mapTo(Protocol_OLD.class);
//                }catch (Exception ignore){
//                    logger.error("Protocol Error");
//                    socket.write(ProtocalFactory.createProtocolErrorResponse(
//                            socket.localAddress().host(),socket.remoteAddress().host()));
//                    return;
//                }
//                if(null == protocolOLD || null == protocolOLD.getHeader()){
//                    logger.error("Protocol Error");
//                    socket.write(ProtocalFactory.createProtocolErrorResponse(
//                            socket.localAddress().host(),socket.remoteAddress().host()));
//                }else {
//                    protocolOLD.getHeader().setSocketId(socket.writeHandlerID());
//                    Environment.registerSocket(socket);
//                    String address = ProtocolType.C.getProtocolHandlerEventAddressByType(protocolOLD.getHeader().getType());
//                    if(StringUtils.isEmpty(address)){
//                        logger.warn("protocol type not support");
//                    }else {
//                        vertx.eventBus().send(address, protocolOLD);
//                    }
//                }
//            });
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
