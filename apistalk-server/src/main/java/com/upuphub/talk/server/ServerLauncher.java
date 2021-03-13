package com.upuphub.talk.server;

import com.upuphub.talk.server.network.Gateway;
import com.upuphub.talk.server.network.tcp.TcpGateway;
import com.upuphub.talk.server.network.udp.UDPGateway;
import com.upuphub.talk.server.handler.system.ProtocolRegisterHandler;
import com.upuphub.talk.server.protocolold.Protocol_OLD;
import com.upuphub.talk.server.protocolold.ProtocolMsgCodec;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * 服务启动入口
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 11:35
 **/
public abstract class ServerLauncher {
    public static int supportedGateways = 0;
    private boolean running = false;
    private Gateway TCP_GATEWAY;
    private Gateway UDP_GATEWAY;

    protected void initGateways()
    {
        if(Gateway.isSupportUDP(supportedGateways))
        {
            UDP_GATEWAY = new UDPGateway();
        }

        if(Gateway.isSupportTCP(supportedGateways))
        {
            TCP_GATEWAY = new TcpGateway();
        }
    }

    public void startup() throws Exception{
        Environment environment = new Environment(Vertx.vertx());
        Environment.getVertx().eventBus().registerDefaultCodec(Protocol_OLD.class, ProtocolMsgCodec.create());
        Environment.getVertx().deployVerticle(TcpGateway.class.getName(), new DeploymentOptions().setInstances(8));
        Environment.getVertx().deployVerticle(ProtocolRegisterHandler.class.getName(),new DeploymentOptions().setInstances(8));
    }


}
