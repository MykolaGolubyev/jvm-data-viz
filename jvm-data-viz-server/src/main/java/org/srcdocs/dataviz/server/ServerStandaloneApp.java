package org.srcdocs.dataviz.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;

/**
 * @author mykola
 */
public class ServerStandaloneApp {
    public static void main(String[] args) {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        try {
            ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
            container.addEndpoint(CommunicationSocket.class);

            server.start();
            server.dump(System.err);
            server.join();
        }
        catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
