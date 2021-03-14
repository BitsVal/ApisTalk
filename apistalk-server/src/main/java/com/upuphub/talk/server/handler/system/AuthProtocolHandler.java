package com.upuphub.talk.server.handler.system;

import com.upuphub.talk.server.factory.ProtocolFactory;
import com.upuphub.talk.server.factory.ProtocolPackageFactory;
import com.upuphub.talk.server.protocol.*;
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
    CMD command() {
        return CMD.CMD_AUTHORIZATION_REQ;
    }


    @Override
    public void handler(Message<ProtocolPackage> protocolMsg) throws Exception {
        Protocol protocolReq = protocolMsg.body().getProtocol();
        AuthorizationReq authorizationReq = AuthorizationReq.parseFrom(protocolReq.getData());
        String token = authorizationReq.getToken();
        String from = protocolReq.getHeader().getFrom();
        Protocol protocolRsp = ProtocolFactory.buildAuthorizationRsp(protocolReq.getHeader().getFrom());
        ProtocolPackage protocolPackage = ProtocolPackageFactory.buildProtocolPackageByProtocolRsp(protocolRsp, HANDLER_CODE.SUCCESS);
        protocolMsg.reply(protocolPackage);
    }
}
