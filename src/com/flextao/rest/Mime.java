
package com.flextao.rest;

import java.util.HashMap;
import java.util.Map;

public class Mime {
    public static final String JSON = "application/json";
    public static final String XML = "application/xml";
    public static final Map<String, String> MIME_TYPES = new HashMap<String, String>();
    static {
        MIME_TYPES.put(JSON, "json");
        MIME_TYPES.put(XML, "xml");
    }
}
