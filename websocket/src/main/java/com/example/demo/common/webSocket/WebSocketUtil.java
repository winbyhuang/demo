package com.example.demo.common.webSocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author winby
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketUtil implements Serializable {
    private static final long serialVersionUID = -2992666788287965958L;
    private static int onlineCount = 0;

    //    private static CopyOnWriteArraySet<WebSocketUtil> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 所有连接
     */
    public static ConcurrentHashMap<String, WebSocketUtil> userMap = new ConcurrentHashMap<String, WebSocketUtil>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        userMap.put(session.getId(), this);
        addOnlineCount();
        String message = "有新链接" + session.getId() + "加入!当前在线人数为" + getOnlineCount();
        System.out.println(message);
        sendSome(message, userMap);
    }

    @OnClose
    public void onClose() {
        userMap.remove(this.session.getId());
        subOnlineCount();
        System.out.println("有一链接" + session.getId() + "关闭!当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("来自" + session.getId() + "客户端的消息:" + message);
        sendMessage(userMap,message);
    }

    public static void sendSome(String message, ConcurrentHashMap<String, WebSocketUtil> map) throws IOException {
        for (ConcurrentHashMap.Entry<String, WebSocketUtil> entry : map.entrySet()) {
            entry.getValue().session.getBasicRemote().sendText(message);
        }
    }

    public void sendMessage(ConcurrentHashMap<String, WebSocketUtil> map, String message) throws IOException {
        for (ConcurrentHashMap.Entry<String, WebSocketUtil> entry : map.entrySet()) {
            entry.getValue().session.getBasicRemote().sendText(message);
        }
//        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return WebSocketUtil.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketUtil.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketUtil.onlineCount--;
    }
}