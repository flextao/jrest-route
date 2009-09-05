/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.lang.reflect.Method;

public class Route {

    private final ResourceRequest request;
    private final ResourceResponse response;
    private final ResourceMap map;
    private final ResourceInfo info;

    public Route(ResourceRequest request, ResourceResponse response, ResourceMap map) {
        this.info = ResourceInfo.from(request.getResourceURI());
        this.request = request;
        this.response = response;
        this.map = map;
    }

    public Object doGet() {
        return info.isList() ? resourceController().list() : onResource("show");
    }

    public String create() {
        String resourceId = resourceController().create();
        return F.uri(info.getResourceName(), resourceId);
    }

    public Object update() {
        return onResource("update");
    }

    public void destroy() {
        onResource("destroy");
    }

    private Object onResource(String method) {
        Method behaviour = F.getMethod(ResourceController.class, method);
        Object res = F.invoke(behaviour, resourceController(), info.getResourceId());
        if (res == null) {
            throw new ResourceNotFoundException(info);
        }
        return res;
    }

    private ResourceController<?> resourceController() {
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
