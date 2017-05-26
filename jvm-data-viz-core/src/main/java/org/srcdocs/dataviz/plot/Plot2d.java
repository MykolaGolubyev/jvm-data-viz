package org.srcdocs.dataviz.plot;

import org.srcdocs.dataviz.VizPayload;

import java.util.Arrays;

/**
 * @author mykola
 */
public class Plot2d implements VizPayload {
    private Integer test;

    public Plot2d(Integer test) {
        this.test = test;
    }

    @Override
    public Object serializableData() {
        return Arrays.asList(test, test + 2, test + 3, test + 4);
    }

    @Override
    public String type() {
        return "plot2d";
    }
}
