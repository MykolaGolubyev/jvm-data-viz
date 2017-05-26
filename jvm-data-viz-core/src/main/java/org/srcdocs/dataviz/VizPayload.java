package org.srcdocs.dataviz;

/**
 * @author mykola
 */
public interface VizPayload {
    Object serializableData();
    String type();
}
