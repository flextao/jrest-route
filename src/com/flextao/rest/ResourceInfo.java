
package com.flextao.rest;

public class ResourceInfo {

    public static ResourceInfo from(String resourceUri) {
        ResourceInfo info = new ResourceInfo();
        for (String str : resourceUri.split("/")) {
            if (F.isBlank(info.getResourceName())) {
                info.setResourceName(str);
            } else if (F.isBlank(info.getResourceId())) {
                info.setResourceId(str);
            }
        }
        return info;
    }

    private String resourceName;
    private String resourceId;

    /**
     * check the resource info is a resource list or just one resource item.
     */
    public boolean isList() {
        return F.isBlank(getResourceId());
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String resourceNotFoundMessage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Couldn't find resource[");
        buffer.append(getResourceName());
        buffer.append("]");
        if (!F.isBlank(this.getResourceId())) {
            buffer.append(" by id[");
            buffer.append(this.getResourceId());
            buffer.append("]");
        }
        return buffer.toString();
    }
}
