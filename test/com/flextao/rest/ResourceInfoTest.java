package com.flextao.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResourceInfoTest {
    // @Test
    // public void xxx() {
    // //(([^/]+)/([^/]+))*
    // Pattern pattern =
    // Pattern.compile("/?([^/\\.]+)/?([^/\\.]+)?(\\.([\\w]+))?/?$");
    // for (String str : Arrays.asList("res/id.xml", "res.xml", "res", "res/id",
    // "res/id/", "/res/id", "/res/")) {
    // Matcher matcher = pattern.matcher(str);
    // if (matcher.matches()) {
    // System.out.println(str + ": matchs ");
    // System.out.println("  group: " + matcher.group());
    // System.out.println("  group: " + matcher.groupCount());
    // for (int i = 0; i <= matcher.groupCount(); i++) {
    // System.out.println("  group" + i + ": " + matcher.group(i));
    // }
    // }
    // }
    //        
    // }
    @Test
    public void parse_resource_uri() {
        assertResourceInfo("res", "id", ResourceInfo.from("res/id"));
        assertResourceInfo("res", "id", ResourceInfo.from("/res/id/"));
        assertResourceInfo("res", null, ResourceInfo.from("res"));
        assertResourceInfo("id", null, ResourceInfo.from("/id//"));
    }

    @Test
    public void parse_format_from_resource_uri() {
        assertResourceInfo("users", null, "json", ResourceInfo.from("users.json"));
        assertResourceInfo("users", "id", "json", ResourceInfo.from("users/id.json"));
        assertResourceInfo("users", "id", "xml", ResourceInfo.from("/users/id.xml"));
    }

    private void assertResourceInfo(String resource, String id, String format, ResourceInfo info) {
        assertEquals(resource, info.getResourceName());
        assertEquals(id, info.getResourceId());
        assertEquals(format, info.getFormatName());
    }

    private void assertResourceInfo(String name, String id, ResourceInfo info) {
        assertResourceInfo(name, id, "json", info);
    }
}
