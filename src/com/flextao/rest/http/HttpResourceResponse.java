/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest.http;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flextao.rest.ResourceResponse;
import com.flextao.rest.URIConverter;

public class HttpResourceResponse implements ResourceResponse {

    private final HttpServletResponse resp;
    private final HttpServletRequest req;

    public HttpResourceResponse(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public void write(String body) {
        try {
            resp.getWriter().print(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createdResourceURI(String uri) {
        String requestURI = URIConverter.getInstance().requestURI(req.getContextPath(), uri);
        this.resp.addHeader("created", requestURI);
    }

}
