package com.upuphub.talk.server.handler.system;

import com.upuphub.talk.server.Environment;
import com.upuphub.talk.server.protocolold.Protocol_OLD;
import com.upuphub.talk.server.protocolold.ProtocolType;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register Protocol Process
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 22:37
 **/
public class ProtocolCommonDataHandler extends AbstractSystemApisHandler {
    private final Logger logger = LoggerFactory.getLogger(ProtocolCommonDataHandler.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @Override
    ProtocolType.C bindProtocolType() {
        return ProtocolType.C.FROM_CLIENT_TYPE_OF_COMMON$DATA;
    }

    @Override
    public void handler(Message<Protocol_OLD> protocolMsg) {
        Protocol_OLD protocolOLD = protocolMsg.body();
        if(Environment.isRegistered(protocolOLD.getHeader().getFrom())){
            // todo 这里返回重复注册
            logger.info("这里用户重复注册注册了");
            vertx.eventBus().send(protocolOLD.getHeader().getSocketId(),Buffer.buffer("尔等重复注册了"));
        }else {
            if("token".equals(protocolOLD.getData())){
                // todo 这里可以通过验证某些参数,验证用户能否正常合法建立连接,不能合法建立连接,直接关闭该次TCP链接
                Environment.registerConnectionSession(
                        protocolOLD.getHeader().getFrom(),
                        protocolOLD.getHeader().getSocketId());
                vertx.eventBus().send(protocolOLD.getHeader().getSocketId(), Buffer.buffer("尔等注册成功了"));
            }else {
                vertx.eventBus().send(protocolOLD.getHeader().getSocketId(),Buffer.buffer("拜拜了,尔等token正确"));
                Environment.pickOutSocketBySocketId(protocolOLD.getHeader().getSocketId());
            }
        }

    }


}
