package org.srcdocs.dataviz.utils

import org.junit.Test

/**
 * @author mykola
 */
class ResourceUtilsTest {
    @Test
    void "should read text from a single resource file"() {
        def content = ResourceUtils.textContent("single.txt")
        assert content == "single resource\nfile"
    }
}
