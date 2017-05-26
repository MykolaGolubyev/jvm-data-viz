package org.srcdocs.dataviz.client;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

/**
 * @author mykola
 */
public class CommunicationClient {
    private final Session session;

    public CommunicationClient() {
        URI uri = URI.create("ws://localhost:8080/viz");

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            session = container.connectToServer(ClientSocket.class, uri);
        } catch (DeploymentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new CommunicationClient().sendText("tter2");
    }
}
