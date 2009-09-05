
package com.flextao.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class URIConverterTest {

    @Test
    public void resourceURI() {
        URIConverter converter = new URIConverter();
        converter.setResourcePrefix("resources");
        String result = converter.resourceURI("/doit", "/doit/resources/task");
        assertEquals("task", result);
    }
}
