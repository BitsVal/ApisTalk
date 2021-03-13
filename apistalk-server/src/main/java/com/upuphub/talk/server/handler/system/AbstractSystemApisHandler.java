package com.upuphub.talk.server.handler.system;

import com.upuphub.talk.server.handler.AbstractApisHandler;
import com.upuphub.talk.server.protocolold.ProtocolType;
import io.vertx.core.Promise;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 20:44
 **/
public abstract class AbstractSystemApisHandler extends AbstractApisHandler {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @Override
    public String bindEventAddress() {
        return bindProtocolType().getAddress();
    }

    abstract ProtocolType.C bindProtocolType();
}
