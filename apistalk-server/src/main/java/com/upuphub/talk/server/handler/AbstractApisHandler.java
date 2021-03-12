package com.upuphub.talk.server.handler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 17:59
 **/
public abstract class AbstractApisHandler extends AbstractVerticle implements ApisHandler {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().consumer(bindEventAddress(),this::handler);
        super.start(startPromise);
    }
}
