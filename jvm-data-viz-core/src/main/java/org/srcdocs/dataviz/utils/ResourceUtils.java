package org.srcdocs.dataviz.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author mykola
 */
public class ResourceUtils {
    private ResourceUtils() {
    }

    /**
     * {@link InputStream} of the specified resource
     * @param resourcePath resource path like path/to/meta.json
     * @return input stream
     */
    public static InputStream resourceStream(String resourcePath) {
        InputStream stream = ResourceUtils.class.getClassLoader().getResourceAsStream(resourcePath);
        if (stream == null) {
            throw new IllegalArgumentException("can't find resource: " + resourcePath);
        }
        return stream;
    }

    /**
     * textual content from the classpath by resource path
     * @param resourcePath resource path like path/to/meta.json
     * @return text content of the resource
     */
    public static String textContent(String resourcePath) {
        InputStream stream = resourceStream(resourcePath);

        try {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
