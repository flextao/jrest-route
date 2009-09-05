/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest.http;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.flextao.rest.F;
import com.flextao.rest.ResourceRequest;
import com.flextao.rest.URIConverter;

public class HttpResourceRequest implements ResourceRequest {

    private final HttpServletRequest req;

    public HttpResourceRequest(HttpServletRequest req) {
        this.req = req;
    }

    public String getResourceURI() {
        return URIConverter.getInstance().resourceURI(req.getContextPath(), req.getRequestURI());
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
