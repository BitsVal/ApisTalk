package com.upuphub.talk.server.hanlder;

import com.upuphub.talk.server.content.SocketInitContent;
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

    public GlobalInboundHandler(NetSocket socket) {
        // tcp连接建立完成后会走到这里
        // 需要做一个踢出的操作
        logger.info("Call 1");
        SocketInitContent.putSocket(socket,5000L);
//        Protocol.Header header = new Protocol.Header(
//                "123","456", ProtocolType.C.FROM_CLIENT_TYPE_OF_KEEP$ALIVE,(byte)1,2,"as","ds"
//        );
//        Protocol protocol = new Protocol(header, Collections.emptyList(),"XXXX");
//        socket.write(JsonFactory.INSTANCE.codec().toBuffer(protocol));
    }

    @Override
    public void handle(Buffer event) {
        logger.info("Call 2");
        Protocol.Header header = event.toJsonObject()
                .getJsonObject(Protocol.R.HEADER)
                .mapTo(Protocol.Header.class);
        if(null == header){
            logger.error("Protocol Error");
        }else {
            switch (header.getType()){
                case ProtocolType.C.FROM_CLIENT_TYPE_OF_REGISTER:
                    logger.error("Protocol Error");
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
