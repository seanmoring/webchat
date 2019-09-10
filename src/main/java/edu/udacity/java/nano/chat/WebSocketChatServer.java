package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {

        //TODO: add send message method.
        Iterator i = onlineSessions.keySet().iterator();
        while (i.hasNext()) {
            try {
                onlineSessions.get(i.next()).getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        onlineSessions.put(session.getId(), session);

        Message msg = new Message();
        msg.setOnlineCount(onlineSessions.size());
        msg.setType("ENTER");

        sendMessageToAll(JSON.toJSONString(msg));

        /*Iterator i = onlineSessions.keySet().iterator();
        while (i.hasNext()) {
            try {
                onlineSessions.get(i.next()).getBasicRemote().sendText(JSON.toJSONString(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.

        Message m = JSON.parseObject(jsonStr, Message.class);
        m.setOnlineCount(onlineSessions.size());
        m.setType("SPEAK");

        sendMessageToAll(JSON.toJSONString(m));

        /*
        Iterator i = onlineSessions.keySet().iterator();
        while (i.hasNext()) {
            try {
                onlineSessions.get(i.next()).getBasicRemote().sendText(JSON.toJSONString(m));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */

    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.

        onlineSessions.remove(session.getId());
        Message msg = new Message();
        msg.setOnlineCount(onlineSessions.size());
        msg.setType("LEAVE");

        sendMessageToAll(JSON.toJSONString(msg));
        /*
        Iterator i = onlineSessions.keySet().iterator();
        while (i.hasNext()) {
            try {
                onlineSessions.get(i.next()).getBasicRemote().sendText(JSON.toJSONString(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         */
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
