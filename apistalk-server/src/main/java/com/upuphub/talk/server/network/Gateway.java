package com.upuphub.talk.server.network;

import io.vertx.core.AbstractVerticle;

/**
 * Server Getaway
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 11:41
 **/
public abstract class Gateway extends AbstractVerticle {
    public static final int SUPPORT_UDP = 0x0001;
    public static final int SUPPORT_TCP = 0x0002;


    protected int port;
    protected String host = "0.0.0.0";


    public static boolean isSupportUDP(int support)
    {
        return (support & SUPPORT_UDP) == SUPPORT_UDP;
    }

    public static boolean isSupportTCP(int support)
    {
        return (support & SUPPORT_TCP) == SUPPORT_TCP;
    }

    protected abstract void before();


}
