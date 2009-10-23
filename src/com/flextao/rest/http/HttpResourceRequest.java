package com.flextao.rest.http;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.flextao.rest.F;
import com.flextao.rest.ResourceRequest;
import com.flextao.rest.URIConverter;

public class HttpResourceRequest implements ResourceRequest {

    public final HttpServletRequest req;
    private final URIConverter converter;

    public HttpResourceRequest(HttpServletRequest req, URIConverter converter) {
        this.req = req;
        this.converter = converter;
    }

    public String getContentType() {
        return this.req.getContentType();
    }

    public String getResourceURI() {
        return converter.resourceURI(req.getContextPath(), req.getRequestURI());
    }

    public String getParameter(String name) {
        return req.getParameter(name);
    }

    public String[] getParameterValues(String name) {
        return req.getParameterValues(name);
    }

    public String getInputContent() {
        String charset = F.parseCharsetFromContentType(getContentType());
        if (charset == null) {
            charset = System.getProperty("file.encoding");
        }
        try {
            return F.read(req.getInputStream(), charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
