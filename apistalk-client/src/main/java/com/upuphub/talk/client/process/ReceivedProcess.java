package com.upuphub.talk.client.process;

import com.upuphub.talk.client.protocol_old.ProtocolOld;
import com.upuphub.talk.client.protocol_old.ProtocolType;
import io.vertx.core.eventbus.Message;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-11 21:44
 **/
public class ReceivedProcess extends AbstractProtocolProcess{
    @Override
    String bindEventAddress() {
        return ProtocolType.S.FROM_SERVER_TYPE_OF_RECEIVED_EVENT_ADDRESS;
    }

    @Override
    void handler(Message<ProtocolOld> protocolMsg) {


    }
}
