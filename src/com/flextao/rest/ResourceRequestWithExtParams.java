/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.util.Map;

public class ResourceRequestWithExtParams implements ResourceRequest {

    private final ResourceRequest request;
    private final Map<String, String> params;

    public ResourceRequestWithExtParams(ResourceRequest request, Map<String, String> params) {
        this.request = request;
        this.params = params;
    }

    public String getInputContent() {
        return this.request.getInputContent();
    }

    public String getParameter(String name) {
        if (this.params.containsKey(name)) {
            return this.params.get(name);
        }
        return this.request.getParameter(name);
    }

    public String[] getParameterValues(String name) {
        return this.request.getParameterValues(name);
    }

    public String getResourceURI() {
        return this.request.getResourceURI();
    }

}
