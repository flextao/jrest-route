
package com.flextao.rest;

import java.util.Map;

public class ResourceRequestWithExtParams implements ResourceRequest {

    private final ResourceRequest request;
    private final Map<String, String> params;

    public ResourceRequestWithExtParams(ResourceRequest request, Map<String, String> params) {
        this.request = request;
        this.params = params;
    }

    public String getInputContent() {
        return this.request.getInputContent();
    }

    public String getParameter(String name) {
        if (this.params.containsKey(name)) {
            return this.params.get(name);
        }
        return this.request.getParameter(name);
    }

    public String[] getParameterValues(String name) {
        return this.request.getParameterValues(name);
    }

    public String getResourceURI() {
        return this.request.getResourceURI();
    }

    public String getContentType() {
        return this.request.getContentType();
    }

}
