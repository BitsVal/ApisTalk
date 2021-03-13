package com.upuphub.talk.server.network.tcp;

import com.upuphub.talk.server.Environment;
import com.upuphub.talk.server.factory.ProtocalFactory;
import com.upuphub.talk.server.network.Gateway;
import com.upuphub.talk.server.protocolold.Protocol_OLD;
import com.upuphub.talk.server.protocolold.ProtocolType;
import com.upuphub.talk.server.utils.StringUtils;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;
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
            RecordParser recordParser = RecordParser.newFixed(4);
            recordParser.setOutput(new Handler<Buffer>() {
                // 表示当前数据长度
                int size = -1;
                @Override
                public void handle(Buffer event) {
                    // -1表示当前还没有长度信息，需要从收到的数据中取出长度
                    if (-1 == size) {
                        // 取出长度
                        size = event.getInt(0);
                        // 动态修改长度
                        recordParser.fixedSizeMode(size);

                    } else {
                        // 如果size != -1, 说明已经接受到长度信息了，接下来的数据就是protobuf可识别的字节数组
                        byte[] buf = event.getBytes();
//                        Message msg = null;
//                        try {
//                            msg = Message.parseFrom(buf);
//                        } catch (InvalidProtocolBufferException e) {
//                            System.out.println(e.getMessage());
//                            socket.close();
//                            return;
//                        }
                        // 处理完后要将长度改回4
                        recordParser.fixedSizeMode(4);
                        // 重置size变量
                        size = -1;
                    }
                }
            });
            socket.handler(recordParser);//event -> {
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
