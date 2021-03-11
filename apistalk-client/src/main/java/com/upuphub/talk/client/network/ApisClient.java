package com.upuphub.talk.client.network;

import io.vertx.core.AbstractVerticle;

/**
 * 客户端
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-11 18:20
 **/
public abstract class ApisClient extends AbstractVerticle {
    public static final int SUPPORT_UDP = 0x0001;
    public static final int SUPPORT_TCP = 0x0002;

    protected int port;
    protected String host = "127.0.0.1";


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
