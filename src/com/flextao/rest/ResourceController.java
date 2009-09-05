
package com.flextao.rest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Extends this class to implement your own resource controller to process
 * list/show/create/update/destroy action on resource
 * 
 * @param <R> Resource
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
     * @param resource deserialized from request input content
     * @return created resource id
     */
    public abstract String create(R resource);

    /**
     * @param resourceId
     * @return Resource object showed, it means resource not found when return
     *         null
     */
    public abstract R show(String id);

    /**
     * @param resourceId
     * @param resource deserialized from request input content
     * @return Resource object updated, it means resource not found when return
     *         null
     */
    public abstract R update(String id, R resource);

    /**
     * @param resourceId
     * @return Resource object destroyed, it means resource not found when
     *         return null
     */
    public abstract R destroy(String id);

    public ResourceRequest getRequest() {
        return request;
    }

    public ResourceResponse getResponse() {
        return response;
    }

    /**
     * The resource class parsed from generic type specified by sub-class
     */
    @SuppressWarnings("unchecked")
    public Class<R> resourceClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<R>) parameterizedType.getActualTypeArguments()[0];
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) ((Class) type)
                    .getGenericSuperclass();
            return (Class<R>) parameterizedType.getActualTypeArguments()[0];
        }
    }
}
