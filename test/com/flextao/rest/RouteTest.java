package com.flextao.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.flextao.rest.test.TestResourceRequest;
import com.flextao.rest.test.TestResourceResponse;
import com.google.gson.Gson;

public class RouteTest {

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
    public void get_list_of_empty_resources() {
        request.setResourceUri(resourceName);
        route.doGet(request, response);
        assertEquals(0, responseResources().length);
    }

    @Test
    public void get_list_of_resources_by_condition() {
        dao.add(new AResource(1));
        dao.add(new AResource(2));
        dao.add(new AResource(3));
        request.setResourceUri(resourceName);
        request.addParam("resource_id", "2");
        route.doGet(request, response);
        AResource[] resources = responseResources();
        assertEquals(1, resources.length);
        assertEquals(2, resources[0].getId());
    }

    @Test
    public void create_a_resource_by_post() {
        request.setResourceUri(resourceName);
        request.setBody(gson.toJson(new AResource(123)));
        route.doPost(request, response);

        assertEquals(1, dao.resources().size());
        assertEquals(123, dao.resources().get(0).getId());
        assertEquals(resourceName + "/123", response.getCreatedResourceURI());
    }

    @Test
    public void get_a_resource_by_id() {
        dao.add(new AResource(321));
        request.setResourceUri(resourceName + "/321");
        route.doGet(request, response);
        AResource result = gson.fromJson(response.body(), AResource.class);
        assertEquals(321, result.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_resource_not_found_exception_when_trying_to_get_a_resource_with_wrong_id() {
        request.setResourceUri(resourceName + "/321");
        route.doGet(request, response);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_resource_not_found_exception_when_trying_to_get_a_resource_not_exist() {
        request.setResourceUri("not exist");
        route.doGet(request, response);
    }

    @Test
    public void update_resource_by_put() {
        dao.add(new AResource(321));
        request.setResourceUri(resourceName + "/321");
        request.setBody(gson.toJson(new AResource(321, "res name")));
        route.doPut(request, response);
        AResource result = gson.fromJson(response.body(), AResource.class);
        assertEquals("res name", result.getName());
        assertEquals("res name", dao.findResourceById("321").getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_resource_not_found_exception_when_trying_to_update_a_resource_with_wrong_id() {
        request.setResourceUri(resourceName + "/321");
        request.setBody(gson.toJson(new AResource(321, "res name")));
        route.doPut(request, response);
    }

    @Test
    public void destroy_resource_by_delete() {
        dao.add(new AResource(321));
        request.setResourceUri(resourceName + "/321");
        route.doDelete(request, response);
        assertEquals(0, dao.resources().size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_resource_not_found_exception_when_trying_to_destroy_a_resource_with_wrong_id() {
        request.setResourceUri(resourceName + "/321");
        route.doDelete(request, response);
    }

    @Test
    public void should_throw_resource_not_found_exception_when_resource_uri_is_invalid() {
        dao.add(new AResource(321));
        request.setResourceUri(resourceName + "/321..");
        try {
            route.doGet(request, response);
            fail();
        } catch (ResourceNotFoundException e) {
            assertTrue(e.getMessage().contains("321.."));
        }
    }

    @Test
    public void get_list_of_resources_by_param_in_resource_uri() {
        dao.add(new AResource(1));
        dao.add(new AResource(2));
        dao.add(new AResource(3));
        request.setResourceUri("resources/2/" + resourceName);
        route.doGet(request, response);
        AResource[] resources = responseResources();
        assertEquals(1, resources.length);
        assertEquals(2, resources[0].getId());
    }

    private AResource[] responseResources() {
        return gson.fromJson(response.body(), AResource[].class);
    }
}
