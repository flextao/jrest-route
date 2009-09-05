/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flextao.rest.ResourceNotFoundException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * It's a simple basic auth filter implementation for handling rest request. You
 * need implement your own authorize method to authorize username and password
 * decoded from Basic Auth request header. When the request is not include
 * Authorization header, it will response UNAUTHORIZED (401) status code and do
 * nothing else. When the username and password authorized failed, it will
 * response FORBIDDEN (401) status code and do nothing else. While processing
 * the request, it will catch ResourceNotFoundException and response NOT_FOUND
 * (404).
 */
public abstract class AbstractBasicAuthFilter implements Filter {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String WWW_AUTHENTICATE_HEADER_NAME = "WWW-Authenticate";
    private static final String BASIC_HEADER_PREFIX = "Basic ";

    /**
     * Do nothing inside
     */
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Auth process message for debuging
     * 
     * @param message
     */
    protected abstract void log(String message);

    /**
     * Authorize the username and password and setup Session or Application
     * status as you need
     * 
     * @param username
     * @param password
     * @return Return true if authorize success, otherwize return false which
     *         would cause response http FORBIDDEN (401) status
     */
    protected abstract boolean authorize(String username, String password);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");
        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        this.doFilterHttp(httpRequest, httpResponse, chain);
    }

    private void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);

        if (!isBasicAuth(header)) {
            unauthorizedRequest(response);
            return;
        }

        if (decodeAndAuthorize(header)) {
            authSuccessDoFilter(request, response, chain);
        } else {
            log("Authentication Forbidden");
            response.setStatus(HttpStatus.FORBIDDEN);
        }
    }

    private void unauthorizedRequest(HttpServletResponse response) {
        log("Unauthorized request");
        response.addHeader(WWW_AUTHENTICATE_HEADER_NAME, "Basic realm=\"Welcome\"");
        response.setStatus(HttpStatus.UNAUTHORIZED);
    }

    private void authSuccessDoFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        log("Authentication success");
        try {
            chain.doFilter(request, response);
        } catch (ResourceNotFoundException e) {
            log("Resource not found: " + e.getMessage());
            response.setStatus(HttpStatus.NOT_FOUND);
        }
    }

    private boolean isBasicAuth(String header) {
        return (header != null) && header.startsWith(BASIC_HEADER_PREFIX);
    }

    private boolean decodeAndAuthorize(String header) {
        String base64Token = header.substring(BASIC_HEADER_PREFIX.length());
        String token = new String(Base64.decode(base64Token));

        String username = "";
        String password = "";
        int delim = token.indexOf(":");

        if (delim != -1) {
            username = token.substring(0, delim);
            password = token.substring(delim + 1);
        }

        log("user name: " + username);
        return authorize(username, password);
    }

    public void destroy() {
    }
}
