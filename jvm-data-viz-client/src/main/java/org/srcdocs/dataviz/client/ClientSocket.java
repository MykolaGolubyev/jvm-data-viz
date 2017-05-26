package org.srcdocs.dataviz.client;

import javax.websocket.*;

/**
 * @author mykola
 */
@ClientEndpoint
public class ClientSocket {
    @OnOpen
    public void onWebSocketConnect(Session session) {
        System.out.println("connected to server: " + session);
    }

    @OnMessage
    public void onWebSocketText(String message) {
        System.out.println("client received TEXT message: " + message);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("Socket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace(System.err);
    }

}
