/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

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
