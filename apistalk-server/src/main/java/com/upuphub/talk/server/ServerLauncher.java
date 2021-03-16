package com.upuphub.talk.server;

import com.upuphub.talk.server.codec.ProtocolMsgCodec;
import com.upuphub.talk.server.handler.HandlerManger;
import com.upuphub.talk.server.handler.system.AuthProtocolHandler;
import com.upuphub.talk.server.network.Gateway;
import com.upuphub.talk.server.network.tcp.TcpGateway;
import com.upuphub.talk.server.protocol.ProtocolPackage;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务启动入口
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 11:35
 **/
public abstract class ServerLauncher {
    public static int supportedGateways = 0;

    private boolean running = false;
    private Map<String,Integer> handlerConfigMap = new HashMap<>();

    protected void initGateways()
    {
        if(Gateway.isSupportUDP(supportedGateways)) {
//            handlerConfigMap.put()
        }

        if(Gateway.isSupportTCP(supportedGateways)) {
//            handlerConfigMap.put(TcpGateway.class.getName(),)
        }
    }

    public void startup() throws Exception{
        Environment environment = Environment.getInstance(Vertx.vertx());
        HandlerManger.initProtocolHandler(this.getClass());
        Environment.getVertx().eventBus().registerDefaultCodec(ProtocolPackage.class, ProtocolMsgCodec.create());
        Environment.getVertx().deployVerticle(TcpGateway.class.getName(), new DeploymentOptions().setInstances(8));
        Environment.getVertx().deployVerticle(AuthProtocolHandler.class.getName(),new DeploymentOptions().setInstances(8));
        HandlerManger.getServerApisHandlers().forEach((handler,instances)
                -> Environment.getVertx().deployVerticle(handler.getName(),new DeploymentOptions().setInstances(instances)));

    }


}
