package com.flextao.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jactiveresource.Inflector;

public class ResourceInfo {

    public static ResourceInfo from(String resourceUri) {
        ResourceInfo info = new ResourceInfo();
        Pattern pattern = Pattern.compile("^/?(([^/]+/[^/]+/)+)?([^/\\.]+)/?([^/\\.]+)?(\\.([\\w]+))?/?$");
        Matcher matcher = pattern.matcher(resourceUri);
        if (matcher.matches()) {
            // res/id.xml => group1: null， group2: null， group3: res, group4:
            // id, group5: .xml, group6: xml

            // projects/project_id/users/user_id/res/id.xml => group1:
            // projects/project_id/users/user_id/,
            // group2: users/user_id/
            // group3: res, group4: id, group5: .xml, group6: xml
            info.setupResourceRequestParams(matcher.group(1));
            info.setResourceName(matcher.group(3));
            info.setResourceId(matcher.group(4));
            info.setFormatName(matcher.group(6));
        } else {
            throw new ResourceNotFoundException("Couldn't find resource by uri: " + resourceUri);
        }
        return info;
    }

    private String resourceName;
    private String resourceId;
    private String formatName;
    private Map<String, String> resourceRequestParams = new HashMap<String, String>();

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

    public Map<String, String> getResourceRequestParams() {
        return resourceRequestParams;
    }

    public void setupResourceRequestParams(String paramsUri) {
        if (F.isBlank(paramsUri)) {
            return;
        }
        String[] params = paramsUri.split("/");
        for (int i = 0; i < params.length; i += 2) {
            String foreign_key = Inflector.singularize(params[i]) + "_id";
            String value = params[i + 1];
            resourceRequestParams.put(foreign_key, value);
        }
    }
}
