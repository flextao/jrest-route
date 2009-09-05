
package com.flextao.rest;

import java.util.List;

import com.google.gson.Gson;

/**
 * Extends this class to implement your own resource controller to process
 * list/show/create/update/destroy action on resource
 * 
 * @param <R>
 *            Resource
 */
public abstract class ResourceController<R> {
    private ResourceRequest request;
    private ResourceResponse response;

    public void initialize(ResourceRequest request, ResourceResponse response) {
        this.request = request;
        this.response = response;
    }

    public abstract List<R> list();

    /**
     * @param request
     * @return created resource id
     */
    public abstract String create();

    /**
     * @param resourceId
     * @return Resource object showed, it means resource not found when return
     *         null
     */
    public abstract R show(String resourceId);

    /**
     * @param resourceId
     * @return Resource object updated, it means resource not found when return
     *         null
     */
    public abstract R update(String resourceId);

    /**
     * @param resourceId
     * @return Resource object destroyed, it means resource not found when
     *         return null
     */
    public abstract R destroy(String resourceId);

    public R parse(Class<R> type) {
        String str = this.request.getInputContent();
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    public ResourceRequest getRequest() {
        return request;
    }

    public ResourceResponse getResponse() {
        return response;
    }

}
