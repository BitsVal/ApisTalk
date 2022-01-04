package com.upuphub.apitalk.net.proto;

/**
 * Apis Message Type
 *
 * @author Leo
 * @since ApisTalk:1.0 (2022-01-04)
 **/
public enum ApisCommand {
    /**
     * 连接认证
     */
    CONNECT(1),

    /**
     * 心跳请求包
     */
    HEARTBEAT(2),

    /**
     * 连接断开
     */
    DISCONNECT(3),

    /**
     * 发送消息
     */
    PUBLISH(4),

    /**
     * 异常
     */
    EXCEPTION(5);

    private static final ApisCommand[] VALUES;

    static {
        // this prevent values to be assigned with the wrong order
        // and ensure valueOf to work fine
        final ApisCommand[] values = values();
        VALUES = new ApisCommand[values.length + 1];
        for (ApisCommand mqttMessageType : values) {
            final int value = mqttMessageType.value;
            if (VALUES[value] != null) {
                throw new AssertionError("value already in use: " + value);
            }
            VALUES[value] = mqttMessageType;
        }
    }

    private final int value;

    ApisCommand(int value) {
        this.value = value;
    }

    public static ApisCommand valueOf(int type) {
        if (type <= 0 || type >= VALUES.length) {
            throw new IllegalArgumentException("unknown message type: " + type);
        }
        return VALUES[type];
    }

    public int value() {
        return value;
    }
}
