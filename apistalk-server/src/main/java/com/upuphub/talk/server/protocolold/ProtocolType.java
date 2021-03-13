package com.upuphub.talk.server.protocolold;

/**
 * Apis消息协议类型
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-08 18:10
 **/
public interface ProtocolType {

    /**
     * ProtocolType From Client
     */
    enum C{
        /** 由客户端发出 - 协议类型 注册应用 */
        FROM_CLIENT_TYPE_OF_REGISTER((byte) 0,"apis.s.register"),
        /** 由客户端发出 - 协议类型：心跳包 */
        FROM_CLIENT_TYPE_OF_KEEP$ALIVE((byte) 1,"apis.s.keep.alive"),
        /** 由客户端发出 - 协议类型：发送通用数据 */
        FROM_CLIENT_TYPE_OF_COMMON$DATA((byte) 2,"apis.s.common.data"),
        /** 由客户端发出 - 协议类型：C2S时的回显指令 */
        FROM_CLIENT_TYPE_OF_ECHO((byte) 3,"apis.s.common.echo"),
        /** 由客户端发出 - 协议类型：QoS保证机制中的消息应答包 */
        FROM_CLIENT_TYPE_OF_RECEIVED((byte) 4,"apis.s.received");

        C(byte type, String address) {
            this.type = type;
            this.address = address;
        }

        private final int type;
        private final String address;

        public static String getProtocolHandlerEventAddressByType(byte type) {
            for (C value : values()) {
                if(value.getType() == type){
                    return value.getAddress();
                }
            }
            return null;
        }

        public int getType() {
            return type;
        }

        public String getAddress() {
            return address;
        }
    }

    /**
     * ProtocolType From Server
     */
    public interface S{
        /** 由服务端发出 - 协议类型：反馈给客户端的错误信息 */
        byte FROM_SERVER_TYPE_OF_RESPONSE$FOR$ERROR = -60;
        /** 由服务端发出 - 协议类型：响应客户端的心跳包 */
        byte FROM_SERVER_TYPE_OF_RESPONSE$KEEP$ALIVE = 61;
        /** 由服务端发出 - 协议类型：发送通用数据 */
        byte FROM_SERVER_TYPE_OF_COMMON$DATA =62;
        /** 由服务端发出 - 协议类型：反馈回显指令给客户端 */
        byte FROM_SERVER_TYPE_OF_RESPONSE$ECHO = 63;
        /** 由服务端发出 - 协议类型：QoS保证机制中的消息应答包 */
        byte FROM_SERVER_TYPE_OF_RECEIVED = 64;
    }

    public interface QoS{
        byte LEVEL$0 = 0;
        byte LEVEL$1 = 1;
        byte LEVEL$2 = 2;
    }

}
