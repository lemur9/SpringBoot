package com.bjpowernode;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * websocket服务端
 *
 * ws://localhost:8080/websocket/12345
 */
@ServerEndpoint("/websocket/{uid}")
public class MyWebSocket {

    /**
     * 建立连接，此方法会被调用
     *
     * @param session
     * @param uid
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) throws IOException {
        // 连接成功，发送一个回复给浏览器客户端
        session.getBasicRemote().sendText(uid + "，你好，欢迎连接WebSocket！");
    }

    @OnClose
    public void onClose() {
        System.out.println(this + "关闭连接");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText("服务端接收到的消息." + message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }
}