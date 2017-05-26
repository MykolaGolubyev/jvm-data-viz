package org.srcdocs.dataviz.playground;

import org.junit.Test;
import static org.srcdocs.dataviz.Viz.*;

/**
 * @author mykola
 */
public class CommunicationPlayground {
    @Test
    public void send2dPlot() {
        visualize(plot2d(20));
    }
}
