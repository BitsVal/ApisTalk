package com.upuphub.talk.client.process;

import com.upuphub.talk.client.protocol_old.ProtocolOld;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-11 21:45
 **/
public abstract class AbstractProtocolProcess extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().<ProtocolOld>consumer(bindEventAddress())
                .handler(this::handler);
        startPromise.complete();
    }


    abstract String bindEventAddress();
    abstract void handler(Message<ProtocolOld> protocolMsg);
}
