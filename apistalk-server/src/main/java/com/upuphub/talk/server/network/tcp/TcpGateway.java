package com.upuphub.talk.server.network.tcp;

import com.upuphub.talk.server.hanlder.GlobalInboundHandler;
import com.upuphub.talk.server.hanlder.SocketInitOutHandler;
import com.upuphub.talk.server.network.Gateway;
import com.upuphub.talk.server.process.RegisterProcess;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolMsgCodec;
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
            socket.handler(new GlobalInboundHandler(socket));
            // 监听客户端的退出连接
            socket.closeHandler(close -> logger.info("客户端退出 - [{}]",socket.writeHandlerID()));
        }).listen(port,host, net -> {
            if (net.succeeded()) {
                startPromise.complete();
                logger.info("TcpGateway初始化成功,Host: {},Port: {}",host,port);
            }
        });
        // 这里清理建立了Tcp连接但是不作为的
        vertx.setPeriodic(2000,new SocketInitOutHandler());
        vertx.setPeriodic(2000,event -> {
            vertx.eventBus().publish(id, Buffer.buffer("hello"));
        });
    }

    @Override
    protected void before() {
        port = 8788;
    }
}
