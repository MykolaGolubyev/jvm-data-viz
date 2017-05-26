package org.srcdocs.dataviz;

import org.srcdocs.dataviz.plot.Plot2d;

/**
 * @author mykola
 */
public class Viz {
    public static VizPayload plot2d(Integer test) {
        return new Plot2d(test);
    }

    public static void visualize(VizPayload payload) {
        VizPayloadSenders.send(payload);
    }
}
