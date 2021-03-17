package com.upuphub.talk.server.handler;

/**
 * Handler Type
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-15 16:18
 **/
public enum  HandlerType {
    /**
     * 认证处理请求Handler
     */
    AUTHORIZATION("apis.s.handler.auth"),
    IM_STORE("apis.s.handler.im.store");

    private final String address;

    HandlerType(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
