package com.flextao.rest.test;

import com.flextao.rest.Format;
import com.flextao.rest.ResourceResponse;

public class TestResourceResponse implements ResourceResponse {

    private StringBuffer body = new StringBuffer();
    private String createdResourceURI;
    private Format format;

    public String body() {
        return this.body.toString();
    }

    public void write(String result) {
        this.body.append(result);
    }

    public String getCreatedResourceURI() {
        return createdResourceURI;
    }

    public void createdResourceURI(String uri) {
        createdResourceURI = uri;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Format getFormat() {
        return this.format;
    }

}
