
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
