package com.upuphub.talk.server.process;

import com.upuphub.talk.server.content.SocketOnlineProcess;
import com.upuphub.talk.server.protocol.Protocol;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register Protocol Process
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 22:37
 **/
public class RegisterProcess extends AbstractProtocolProcess {
    private final Logger logger = LoggerFactory.getLogger(RegisterProcess.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @Override
    String bindEventAddress() {
        return "apis.s.register";
    }

    @Override
    void handler(Message<Protocol> protocolMsg) {
        logger.info("Message From {}",protocolMsg.body().getHeader().getFrom());
        Protocol protocol = protocolMsg.body();
        if(SocketOnlineProcess.isRegistered(protocol.getHeader().getFrom())){
            // todo 这里返回重复注册
        }else {
            // todo 这里可以通过验证某些参数,验证用户能否正常合法建立连接,不能合法建立连接,直接关闭该次TCP链接
//            SocketOnlineProcess.putSocket(protocol.getHeader().getFrom(),socket.writeHandlerID());
        }
//        SocketInitProcess.removeSocket(socket);
        // todo 这个返回注册成功的处理
    }


}
