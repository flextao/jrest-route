
package com.flextao.rest.http;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.flextao.rest.F;
import com.flextao.rest.ResourceRequest;
import com.flextao.rest.URIConverter;

public class HttpResourceRequest implements ResourceRequest {

    private final HttpServletRequest req;
    private final URIConverter converter;

    public HttpResourceRequest(HttpServletRequest req, URIConverter converter) {
        this.req = req;
        this.converter = converter;
    }

    public String getResourceURI() {
        return converter.resourceURI(req.getContextPath(), req.getRequestURI());
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getParams() {
        return req.getParameterMap();
    }

    public String getInputContent() {
        try {
            return F.read(req.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
