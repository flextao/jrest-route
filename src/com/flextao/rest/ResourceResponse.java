
package com.flextao.rest;

public interface ResourceResponse {

    /**
     * write a string result into response body
     */
    void write(String body);

    /**
     * created resource uri for response after created a new resource.
     * @param uri it is a relative uri, see {@link ResourceRequest#getResourceURI()}
     */
    void createdResourceURI(String uri);
}
