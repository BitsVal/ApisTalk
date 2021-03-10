package com.upuphub.talk.server.process;

import com.upuphub.talk.server.protocol.Protocol;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 22:57
 **/
public abstract class AbstractProtocolProcess extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().<Protocol>consumer(bindEventAddress())
                .handler(this::handler);
        startPromise.complete();
    }


    abstract String bindEventAddress();
    abstract void handler(Message<Protocol>  protocolMsg);
}
