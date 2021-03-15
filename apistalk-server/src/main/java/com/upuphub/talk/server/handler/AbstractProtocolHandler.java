package com.upuphub.talk.server.handler;

import com.upuphub.talk.server.annotation.ApisHandler;
import com.upuphub.talk.server.exception.ProtocolHandlerEventAddressException;
import com.upuphub.talk.server.protocol.ProtocolPackage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

import java.util.Objects;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 17:59
 **/
public abstract class AbstractProtocolHandler extends AbstractVerticle implements ProtocolHandler {
    protected String bindAddress = null;
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().consumer(bindEventAddress(),this::handler0);
        super.start(startPromise);
    }

    public String bindEventAddress(){
        if(null == bindAddress){
            if(this.getClass().isAnnotationPresent(ApisHandler.class)){
                ApisHandler apisHandler = this.getClass().getDeclaredAnnotation(ApisHandler.class);
                bindAddress = apisHandler.value().getAddress();
                return bindAddress;
            }else {
                throw new ProtocolHandlerEventAddressException("@ApisHandler Not Find");
            }
        }else {
            return bindAddress;
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.bindEventAddress());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj instanceof AbstractProtocolHandler){
            return ((AbstractProtocolHandler) obj).bindEventAddress().equals(this.bindEventAddress());
        }
        return false;
    }

    private void handler0(Message<ProtocolPackage> protocolMsg){
        try {
            handler(protocolMsg);
        }catch (Exception ex){
            protocolMsg.reply("");
        }
    }
}
