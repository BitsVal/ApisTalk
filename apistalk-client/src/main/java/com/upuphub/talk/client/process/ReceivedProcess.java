package com.upuphub.talk.client.process;


import com.upuphub.talk.client.protocol.ProtocolPackage;
import io.vertx.core.eventbus.Message;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-11 21:44
 **/
public class ReceivedProcess extends AbstractProtocolProcess{

    @Override
    String bindEventAddress() {
        return null;
    }

    @Override
    void handler(Message<ProtocolPackage> protocolMsg) {

    }
}
