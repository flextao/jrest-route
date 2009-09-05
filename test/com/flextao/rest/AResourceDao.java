
package com.flextao.rest;

import java.util.ArrayList;
import java.util.List;

public class AResourceDao {
    private static AResourceDao instance = new AResourceDao();

    public static AResourceDao getInstance() {
        return instance;
    }

    private List<AResource> resources = new ArrayList<AResource>();

    public void add(AResource resource) {
        resources.add(resource);
    }

    public List<AResource> resources() {
        return resources;
    }

    public void clear() {
        resources.clear();
    }

    public AResource findResourceById(String resourceId) {
        int theId = Integer.parseInt(resourceId);
        for (AResource res : resources) {
            if (res.getId() == theId) {
                return res;
            }
        }
        return null;
    }

}
