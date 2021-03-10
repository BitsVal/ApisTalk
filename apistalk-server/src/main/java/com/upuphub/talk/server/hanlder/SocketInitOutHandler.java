package com.upuphub.talk.server.hanlder;

import com.upuphub.talk.server.content.SocketInitContent;
import io.vertx.core.Handler;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 15:13
 **/
public class SocketInitOutHandler implements Handler<Long> {
    @Override
    public void handle(Long event) {
        SocketInitContent.getAll().forEach((socket,expireTime)->{
            if(expireTime < System.currentTimeMillis()){
                SocketInitContent.removeSocket(socket);
            }
        });
    }
}
