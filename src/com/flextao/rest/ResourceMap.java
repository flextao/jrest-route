
package com.flextao.rest;

import java.util.HashMap;
import java.util.Map;

public class ResourceMap {

    private Map<String, Class<? extends ResourceController<?>>> map = new HashMap<String, Class<? extends ResourceController<?>>>();

    public void add(String resourceName, Class<? extends ResourceController<?>> resourceClass) {
        map.put(resourceName, resourceClass);
    }

    public Class<? extends ResourceController<?>> get(String resourceName) {
        return map.get(resourceName);
    }

    public void remove(String resourceName) {
        map.remove(resourceName);
    }

    @Override
    public String toString() {
        return "Keys: " + F.join(map.keySet(), ", ");
    }
}
