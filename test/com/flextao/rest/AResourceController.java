package com.flextao.rest;

import java.util.Arrays;
import java.util.List;

public class AResourceController extends ResourceController<AResource> {

    private AResourceDao dao = AResourceDao.getInstance();

    @Override
    public List<AResource> list() {
        String id = getRequest().getParameter("resource_id");
        if (id != null) {
            return Arrays.asList(dao.findResourceById(id));
        } else {
            return dao.resources();
        }
    }

    @Override
    public String create(AResource resource) {
        dao.add(resource);
        return String.valueOf(resource.getId());
    }

    @Override
    public AResource show(String resourceId) {
        return dao.findResourceById(resourceId);
    }

    @Override
    public AResource update(String resourceId, AResource resource) {
        AResource res = dao.findResourceById(resourceId);
        if (res == null) {
            return null;
        }
        res.setName(resource.getName());
        return res;
    }

    @Override
    public AResource destroy(String resourceId) {
        AResource res = dao.findResourceById(resourceId);
        dao.resources().remove(res);
        return res;
    }

}
