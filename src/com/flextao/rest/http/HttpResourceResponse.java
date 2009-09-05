
package com.flextao.rest.http;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flextao.rest.ResourceResponse;
import com.flextao.rest.URIConverter;

public class HttpResourceResponse implements ResourceResponse {

    private final HttpServletResponse resp;
    private final HttpServletRequest req;
    private final URIConverter converter;

    public HttpResourceResponse(HttpServletRequest req, HttpServletResponse resp, URIConverter converter) {
        this.req = req;
        this.resp = resp;
        this.converter = converter;
    }

    public void write(String body) {
        try {
            resp.getWriter().print(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createdResourceURI(String uri) {
        String requestURI = converter.requestURI(req.getContextPath(), uri);
        this.resp.addHeader("created", requestURI);
    }

}
