
package com.flextao.rest.http;

/**
 * Constants enumerating the HTTP status codes. All status codes defined in
 * RFC1945 (HTTP/1.0).
 */
public interface HttpStatus {

    /**
     * 401 Unauthorized (HTTP/1.0 - RFC 1945)
     */
    public static final int UNAUTHORIZED = 401;
    /**
     * 403 Forbidden (HTTP/1.0 - RFC 1945)
     */
    public static final int FORBIDDEN = 403;
    /**
     * 404 Not Found (HTTP/1.0 - RFC 1945)
     */
    public static final int NOT_FOUND = 404;

}
