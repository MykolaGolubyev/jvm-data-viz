package org.srcdocs.dataviz.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author mykola
 */
public class JsonUtils {
    private static final Gson gson = new GsonBuilder().create();

    private JsonUtils() {
    }

    @SuppressWarnings("unchecked")
    public static Map<String, ?> deserializeAsMap(String data) {
        try {
            return gson.fromJson(data, Map.class);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("error parsing " + data, e);
        }
    }
}
