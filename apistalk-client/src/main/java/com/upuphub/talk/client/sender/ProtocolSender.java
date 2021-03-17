package com.upuphub.talk.client.sender;

import com.upuphub.talk.client.protocol.Protocol;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-16 09:15
 **/
public interface ProtocolSender {
    void sendMessage(Protocol protocol);
}
