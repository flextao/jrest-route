/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.lang.reflect.Method;

import com.google.gson.Gson;

public class Routes {
    private ResourceMap map = new ResourceMap();

    public ResourceMap map() {
        return map;
    }

    public void doGet(ResourceRequest request, ResourceResponse response) {
        Route route = new Route(request, response, map);
        Object result = route.doGet();
        response.write(format(result));
    }

    public void doPost(ResourceRequest request, ResourceResponse response) {
        Route route = new Route(request, response, map);
        String resourceURI = route.create();
        response.createdResourceURI(resourceURI);
    }

    public void doPut(ResourceRequest request, ResourceResponse response) {
        Route route = new Route(request, response, map);
        Object result = route.update();
        response.write(format(result));
    }

    public void doDelete(ResourceRequest request, ResourceResponse response) {
        Route route = new Route(request, response, map);
        route.destroy();
    }

    private String format(Object result) {
        Gson gson = new Gson();
        return gson.toJson(result);
    }
}
