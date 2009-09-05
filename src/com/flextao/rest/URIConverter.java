/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

public class URIConverter {

    private String resourcePrefix = "";

    public String requestURI(String contextPath, String resourceURI) {
        return F.uri(contextPath, resourcePrefix, resourceURI);
    }

    public String resourceURI(String contextPath, String requestURI) {
        int prefixLen = (F.uri(contextPath, resourcePrefix) + "/").length();
        return requestURI.substring(prefixLen);
    }

    public void setResourcePrefix(String resourcePrefix) {
        this.resourcePrefix = resourcePrefix;
    }

    public String getResourcePrefix() {
        return resourcePrefix;
    }

}
