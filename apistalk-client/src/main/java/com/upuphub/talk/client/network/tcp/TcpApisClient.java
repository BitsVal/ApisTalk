package com.upuphub.talk.client.network.tcp;

import com.upuphub.talk.client.network.ApisClient;
import com.upuphub.talk.client.protocol.Protocol;
import io.vertx.core.Promise;

/**
 * Tcp客户端实现
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-11 18:21
 **/
public class TcpApisClient extends ApisClient {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.createNetClient().connect(port, host, res->{
            if(res.succeeded()){
                res.result().handler(event -> {
                    Protocol protocol = event.toJsonObject().mapTo(Protocol.class);
                    if(null == protocol || null == protocol.getHeader()){
//                        logger.error("Protocol Error");
                    }
                });
            }
        });
        super.start(startPromise);
    }

    @Override
    protected void before() {

    }
}
