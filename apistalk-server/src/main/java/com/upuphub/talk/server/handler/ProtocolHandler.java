package com.upuphub.talk.server.handler;

import com.upuphub.talk.server.protocol.ProtocolPackage;
import io.vertx.core.eventbus.Message;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 18:00
 **/
public interface ProtocolHandler {
    void handler(Message<ProtocolPackage> protocolMsg) throws Exception;
}
