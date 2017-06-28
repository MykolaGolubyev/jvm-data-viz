package org.srcdocs.dataviz.utils

import org.junit.Test

/**
 * @author mykola
 */
class JsonUtilsTest  {
    @Test
    void "should deserialize json as map"() {
        def json = """{
    "hello": "world",
    "another": {"nested": "value"}} """

        def map = JsonUtils.deserializeAsMap(json)
        assert map == [hello: "world", another: [nested: "value"]]
    }
}
