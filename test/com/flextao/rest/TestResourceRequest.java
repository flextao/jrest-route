/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.util.HashMap;
import java.util.Map;

public class TestResourceRequest implements ResourceRequest {

    private String resourceUri;
    private Map<String, String> params = new HashMap<String, String>();
    private String input;

    public String getResourceURI() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setBody(String input) {
        this.input = input;
    }

    public String getInputContent() {
        return this.input;
    }

}
