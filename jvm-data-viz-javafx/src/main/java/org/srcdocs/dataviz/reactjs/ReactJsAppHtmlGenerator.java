package org.srcdocs.dataviz.reactjs;

import org.srcdocs.dataviz.utils.JsonUtils;
import org.srcdocs.dataviz.utils.ResourceUtils;

import java.util.Map;

/**
 * @author mykola
 */
public class ReactJsAppHtmlGenerator {
    private final String css;
    private final String bundleJavaScript;

    public ReactJsAppHtmlGenerator() {
        Map<String, Object> manifest = loadManifest();

        css = ResourceUtils.textContent(manifest.get("main.css").toString());
        bundleJavaScript = ResourceUtils.textContent(manifest.get("main.js").toString());
    }

    public String getCss() {
        return css;
    }

    public String getBundleJavaScript() {
        return bundleJavaScript;
    }

    public String generateHtml() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<meta charset=\"UTF-8\"/>\n" +
                "<head>\n" +
                "<style>\n" +
                css +
                "</style>" +
                "<title>JVM Visualization</title>" +
                "\n</head>\n" +
                "<body><div id=\"root\"></div>\n" +
                "<script>\n" +
                bundleJavaScript +

                "</script>\n" +
                "\n</body>" +
                "\n</html>\n";

    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> loadManifest() {
        String assetManifest = ResourceUtils.textContent("asset-manifest.json");
        return (Map<String, Object>) JsonUtils.deserializeAsMap(assetManifest);
    }
}
