package com.flextao.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceInfo {

    public static ResourceInfo from(String resourceUri) {
        ResourceInfo info = new ResourceInfo();
        Pattern pattern = Pattern.compile("/?([^/\\.]+)/?([^/\\.]+)?(\\.([\\w]+))?/?$");
        Matcher matcher = pattern.matcher(resourceUri);
        if (matcher.matches()) {
            // res/id.xml =>
            // group1: res
            // group2: id
            // group3: .xml
            // group4: xml
            info.setResourceName(matcher.group(1));
            info.setResourceId(matcher.group(2));
            info.setFormatName(matcher.group(4));
        }
        return info;
    }

    private String resourceName;
    private String resourceId;
    private String formatName;

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

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getFormatName() {
        return formatName == null ? defaultFormat() : formatName;
    }

    public String defaultFormat() {
        return "json";
    }
}
