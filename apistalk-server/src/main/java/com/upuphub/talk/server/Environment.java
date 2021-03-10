package com.upuphub.talk.server;

import com.upuphub.talk.server.protocol.ProtocolMsgCodec;
import io.vertx.core.Vertx;

/**
 * 服务环境
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-10 21:43
 **/
public class Environment {
    private static Environment instance = null;
    private static Vertx vertx;
    private String config;

    public Environment(Vertx vertx) {
        Environment.vertx = vertx;
    }

    public static Vertx getVertx() {
        return Environment.vertx;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
