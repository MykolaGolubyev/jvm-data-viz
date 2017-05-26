package org.srcdocs.dataviz.server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mykola
 */
@ServerEndpoint("/viz")
public class CommunicationSocket {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onWebSocketConnect(Session session) {
        sessions.add(session);
        System.out.println("Socket Connected: " + session);

        try {
            session.getBasicRemote().sendText("{\"hello\": \"world\"}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public void onWebSocketText(String message) {
        System.out.println("Received TEXT message: " + message);
        sessions.forEach(s -> this.sendMessage(s, message));
    }

    @OnClose
    public void onWebSocketClose(Session session, CloseReason reason) {
        sessions.remove(session);
        System.out.println("Socket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace(System.err);
    }

    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
