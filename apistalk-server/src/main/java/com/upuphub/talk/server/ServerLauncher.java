package com.upuphub.talk.server;

import com.upuphub.talk.server.network.Gateway;
import com.upuphub.talk.server.network.tcp.TcpGateway;
import com.upuphub.talk.server.network.udp.UDPGateway;

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

    }
}
