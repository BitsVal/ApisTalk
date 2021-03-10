package com.upuphub.talk.server.hanlder;

import com.upuphub.talk.server.Environment;
import com.upuphub.talk.server.content.SocketInitProcess;
import com.upuphub.talk.server.content.SocketOnlineProcess;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolType;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Handler
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 13:41
 **/
public class GlobalInboundHandler implements Handler<Buffer>{
    private final static Logger logger = LoggerFactory.getLogger(GlobalInboundHandler.class);
    private final NetSocket socket;

    public GlobalInboundHandler(NetSocket socket) {
        // tcp连接建立完成后会走到这里
        // 需要做一个踢出的操作
        this.socket = socket;
        SocketInitProcess.putSocket(socket,5000L);
    }

    @Override
    public void handle(Buffer event) {
        Protocol protocol = event.toJsonObject().mapTo(Protocol.class);
        if(null == protocol || null == protocol.getHeader()){
            logger.error("Protocol Error");
        }else {
            switch (protocol.getHeader().getType()){
                case ProtocolType.C.FROM_CLIENT_TYPE_OF_REGISTER:
                    Environment.getVertx().eventBus().send("apis.s.register",protocol);
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
    }
}
