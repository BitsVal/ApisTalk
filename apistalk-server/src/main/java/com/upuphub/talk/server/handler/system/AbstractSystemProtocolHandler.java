package com.upuphub.talk.server.handler.system;

import com.upuphub.talk.server.factory.ProtocolTypeFactory;
import com.upuphub.talk.server.handler.AbstractProtocolHandler;
import com.upuphub.talk.server.protocol.CMD;
import io.vertx.core.Promise;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 20:44
 **/
public abstract class AbstractSystemProtocolHandler extends AbstractProtocolHandler {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @Override
    public String bindEventAddress() {
        return ProtocolTypeFactory.getEventAddressByCmd(command());
    }



    abstract CMD command();
}
