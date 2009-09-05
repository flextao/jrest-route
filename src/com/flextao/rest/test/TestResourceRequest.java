
package com.flextao.rest.test;

import java.util.HashMap;
import java.util.Map;

import com.flextao.rest.ResourceRequest;

public class TestResourceRequest implements ResourceRequest {

    private String resourceUri;
    private Map<String, String> params = new HashMap<String, String>();
    private String input;

    public String getResourceURI() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setBody(String input) {
        this.input = input;
    }

    public String getInputContent() {
        return this.input;
    }

}
