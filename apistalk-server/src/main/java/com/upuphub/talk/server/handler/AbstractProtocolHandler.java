package com.upuphub.talk.server.handler;

import com.upuphub.talk.server.protocol.ProtocolPackage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 17:59
 **/
public abstract class AbstractProtocolHandler extends AbstractVerticle implements ApisHandler {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().consumer(bindEventAddress(),this::handler0);
        super.start(startPromise);
    }

    private void handler0(Message<ProtocolPackage> protocolMsg){
        try {
            handler(protocolMsg);
        }catch (Exception ex){
            protocolMsg.reply("");
        }
    }
}
