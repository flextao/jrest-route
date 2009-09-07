package com.flextao.rest;

import java.util.HashMap;
import java.util.Map;

import com.flextao.rest.format.JsonFormat;
import com.flextao.rest.format.XmlFormat;

public class Routes {

    private ResourceMap resourceMap = new ResourceMap();
    private URIConverter uriConverter = new URIConverter();
    private Map<String, Format> formats = new HashMap<String, Format>();

    public Routes() {
        formats.put("json", new JsonFormat());
        formats.put("xml", new XmlFormat());
    }

    public URIConverter getURIConverter() {
        return uriConverter;
    }

    public ResourceMap getResourceMap() {
        return resourceMap;
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

    public Map<String, Format> getFormats() {
        return formats;
    }
}
