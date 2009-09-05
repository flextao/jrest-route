/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

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
