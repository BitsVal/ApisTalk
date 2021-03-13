package com.upuphub.talk.server.handler.system;

import com.upuphub.talk.server.factory.ProtocolFactory;
import com.upuphub.talk.server.protocol.CMD;
import com.upuphub.talk.server.protocol.Protocol;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register Protocol Process
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 22:37
 **/
public class AuthProtocolHandler extends AbstractSystemProtocolHandler {
    private final Logger logger = LoggerFactory.getLogger(AuthProtocolHandler.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @Override
    CMD requestCmd() {
        return CMD.CMD_AUTHORIZATION_REQ;
    }


    @Override
    public void handler(Message<Protocol> protocolMsg) {
        protocolMsg.reply(ProtocolFactory.buildAuthorizationReq("1","2","3"));
    }
}
