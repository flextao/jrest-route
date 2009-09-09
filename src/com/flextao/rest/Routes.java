package com.flextao.rest;

import java.util.HashMap;
import java.util.Map;

import com.flextao.rest.format.JsonFormat;
import com.flextao.rest.format.XmlFormat;

/**
 * Routes is the main class of jrest-route. It defines resource map and formats.
 * The resource map defines resource with resource controller. The formats are
 * Routes could recognize and parse. Currently supports json and xml formats.
 * You also could define a resource uri prefix as you need, for example: define
 * all your resources URI start with http://domain/contextPath/prefix/, so that
 * you could put all your resources under a specific URI.
 */
public class Routes {

    private ResourceMap resourceMap = new ResourceMap();
    private URIConverter uriConverter = new URIConverter();
    private Map<String, Format> formats = new HashMap<String, Format>();

    public Routes() {
        formats.put("json", new JsonFormat());
        formats.put("xml", new XmlFormat());
    }

    public void setResourcePrefix(String prefix) {
        uriConverter.setResourcePrefix(prefix);
    }

    public URIConverter getURIConverter() {
        return uriConverter;
    }

    /**
     * @return ResourceMap for defining resource with resource controller
     */
    public ResourceMap getResourceMap() {
        return resourceMap;
    }

    public Map<String, Format> getFormats() {
        return formats;
    }

    public void doGet(ResourceRequest request, ResourceResponse response) {
        Route route = newRoute(request, response);
        response.write(route.doGet());
    }

    public void doPost(ResourceRequest request, ResourceResponse response) {
        Route route = newRoute(request, response);
        String resourceURI = route.create();
        response.createdResourceURI(resourceURI);
    }

    public void doPut(ResourceRequest request, ResourceResponse response) {
        Route route = newRoute(request, response);
        response.write(route.update());
    }

    public void doDelete(ResourceRequest request, ResourceResponse response) {
        Route route = newRoute(request, response);
        route.destroy();
    }

    private Route newRoute(ResourceRequest request, ResourceResponse response) {
        return new Route(request, response, resourceMap, formats);
    }
}
