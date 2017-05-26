package org.srcdocs.dataviz.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.srcdocs.dataviz.VizPayload;
import org.srcdocs.dataviz.VizPayloadSender;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mykola
 */
public class WebSocketVizPayloadSender implements VizPayloadSender {
    private static final Gson gson = new GsonBuilder().create();

    private CommunicationClient client = new CommunicationClient();

    @Override
    public void send(VizPayload payload) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", payload.type());
        map.put("data", payload.serializableData());

        System.out.println("sending " + map);
        client.sendText(gson.toJson(map));
    }
}
