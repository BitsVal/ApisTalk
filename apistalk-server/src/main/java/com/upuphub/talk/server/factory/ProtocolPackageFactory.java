package com.upuphub.talk.server.factory;

import com.upuphub.talk.server.protocol.HANDLER_CODE;
import com.upuphub.talk.server.protocol.Protocol;
import com.upuphub.talk.server.protocol.ProtocolPackage;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-14 10:57
 **/
public class ProtocolPackageFactory {

    public static ProtocolPackage buildProtocolPackageByProtocolRsp(Protocol protocolRsp, HANDLER_CODE code) {
        return ProtocolPackage.newBuilder()
                .setHandlerCode(code)
                .setProtocol(protocolRsp)
                .build();
    }
}
