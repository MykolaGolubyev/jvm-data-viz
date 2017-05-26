package org.srcdocs.dataviz;

import java.util.Set;

/**
 * @author mykola
 */
public class VizPayloadSenders {
    private static Set<VizPayloadSender> senders = ServiceLoaderUtils.load(VizPayloadSender.class);

    public static void send(VizPayload payload) {
        senders.forEach(s -> s.send(payload));
    }
}
