package com.upuphub.talk.example.server.handler;

import com.upuphub.talk.server.annotation.ApisHandler;
import com.upuphub.talk.server.handler.AbstractProtocolHandler;
import com.upuphub.talk.server.handler.HandlerType;
import com.upuphub.talk.server.protocol.AuthorizationReq;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolPackage;
import io.vertx.core.eventbus.Message;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-15 22:27
 **/
@ApisHandler(HandlerType.AUTHORIZATION)
public class AuthProtocolHandler extends AbstractProtocolHandler {

    @Override
    public void handler(Message<ProtocolPackage> protocolMsg) throws Exception {
        Protocol protocolReq = protocolMsg.body().getProtocol();
        AuthorizationReq authorizationReq = AuthorizationReq.parseFrom(protocolMsg.body().getProtocol().getData());
        System.out.println();
        protocolMsg.reply(protocolMsg);
    }
}
