
package com.flextao.rest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.flextao.rest.test.TestResourceRequest;
import com.flextao.rest.test.TestResourceResponse;
import com.google.gson.Gson;

public class FormatTest {

    private Routes route;
    private TestResourceRequest request;
    private TestResourceResponse response;
    private String resourceName;
    private Gson gson;
    private AResourceDao dao;

    @Before
    public void setUp() {
        gson = new Gson();
        route = new Routes();
        request = new TestResourceRequest();
        response = new TestResourceResponse();
        dao = AResourceDao.getInstance();
        resourceName = "test_resource";
        route.getResourceMap().add(resourceName, AResourceController.class);
    }

    @After
    public void tearDown() {
        dao.clear();
    }

    @Test
    public void specify_format_in_uri() {
        dao.add(new AResource(1));
        request.setResourceUri(resourceName + ".json");
        route.doGet(request, response);
        AResource[] resources = responseResources();
        assertEquals(1, resources.length);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_reousrce_not_found_exception_when_specified_an_unknown_format() {
        dao.add(new AResource(1));
        request.setResourceUri(resourceName + ".unknown");
        route.doGet(request, response);
    }

    private AResource[] responseResources() {
        return gson.fromJson(response.body(), AResource[].class);
    }
}
