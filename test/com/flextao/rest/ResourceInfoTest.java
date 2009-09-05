
package com.flextao.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResourceInfoTest {
    @Test
    public void from_resource_uri() {
        assertResourceInfo("res", "id", ResourceInfo.from("res/id"));
        assertResourceInfo("res", "id", ResourceInfo.from("/res/id/"));
        assertResourceInfo("res", null, ResourceInfo.from("res"));
        assertResourceInfo("id", null, ResourceInfo.from("/id//"));
    }

    private void assertResourceInfo(String name, String id, ResourceInfo info) {
        assertEquals(name, info.getResourceName());
        assertEquals(id, info.getResourceId());
    }
}
