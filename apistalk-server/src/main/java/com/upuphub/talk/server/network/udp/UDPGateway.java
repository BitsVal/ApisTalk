package com.upuphub.talk.server.network.udp;

import com.upuphub.talk.server.network.Gateway;
import io.vertx.core.Promise;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.net.SocketAddress;

/**
 * TcpGetaway
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-09 11:43
 **/
public class UDPGateway extends Gateway {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        DatagramSocket datagramSocket = vertx.createDatagramSocket(new DatagramSocketOptions());
        datagramSocket.listen(7777, "0.0.0.0", asyncResult -> {
            System.out.println("开始建立连接：");
            if (asyncResult.succeeded()) {
                SocketAddress address = datagramSocket.localAddress();
                //获取本地服务地址和端口
                String localHost = address.host();
                int localPort = address.port();
                System.out.println(localHost +":"+ localPort);
                datagramSocket.handler(packet -> {
                    //获取远端访问地址和端口，并将消息原路返回。
                    SocketAddress sendAddress = packet.sender();
                    String sendorHost = sendAddress.host();
                    int sendorPort = sendAddress.port();
                    System.out.println(sendorHost+":"+sendorPort);
                    String result = packet.data().toString();
                    System.out.println("接收到的数据：" + result);
                    datagramSocket.send("Message got", sendorPort, sendorHost, handler -> {
                        System.out.println("UDP回复结果： " + handler.succeeded());
                    });
                });
            } else {
                System.out.println("监听失败。");
            }
        });
    }

    @Override
    protected void before() {

    }
}
