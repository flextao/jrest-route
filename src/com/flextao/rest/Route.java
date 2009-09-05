package com.flextao.rest;

import java.lang.reflect.Method;
import java.util.Map;

public class Route {

    private final ResourceRequest request;
    private final ResourceResponse response;
    private final ResourceMap map;
    private final ResourceInfo info;
    private final ResourceController<?> resourceController;
    private final Format format;

    public Route(ResourceRequest request, ResourceResponse response, ResourceMap map, Map<String, Format> formats) {
        this.info = ResourceInfo.from(request.getResourceURI());
        this.format = formats.get(defaultFormat());
        this.request = request;
        this.response = response;
        this.map = map;
        this.resourceController = createResourceController();
    }

    public String defaultFormat() {
        return "json";
    }

    public String doGet() {
        Object resource = info.isList() ? resourceController.list() : onResource("show", info.getResourceId());
        return format.serialize(resource);
    }

    public String create() {
        String resourceId = callResourceController("create", resourceFromRequest());
        return F.uri(info.getResourceName(), resourceId);
    }

    public String update() {
        return format.serialize(onResource("update", info.getResourceId(), resourceFromRequest()));
    }

    public void destroy() {
        onResource("destroy", info.getResourceId());
    }

    private Object onResource(String method, Object... args) {
        Object res = callResourceController(method, args);
        if (res == null) {
            throw new ResourceNotFoundException(info);
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    private <T> T callResourceController(String method, Object... args) {
        Method behaviour = F.getMethod(ResourceController.class, method);
        return (T) F.invoke(behaviour, resourceController, args);
    }

    private Object resourceFromRequest() {
        return format.deserialize(request.getInputContent(), resourceController.resourceClass());
    }

    private ResourceController<?> createResourceController() {
        try {
            ResourceController<?> instance = resourceControllerClass().newInstance();
            instance.initialize(request, response);
            return instance;
        } catch (SecurityException e) {
            throw new IllegalStateException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    private Class<? extends ResourceController<?>> resourceControllerClass() {
        Class<? extends ResourceController<?>> controllerClass = map.get(info.getResourceName());
        if (controllerClass == null) {
            throw new ResourceNotFoundException(info);
        }
        return controllerClass;
    }
}
