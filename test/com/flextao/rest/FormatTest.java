package com.flextao.rest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.flextao.rest.format.XmlFormat;
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
    public void specify_response_format_in_uri() {
        dao.add(new AResource(1));
        request.setResourceUri(resourceName + ".json");
        route.doGet(request, response);
        AResource[] resources = responseResources();
        assertEquals(1, resources.length);
    }

    @Test
    public void use_json_as_default_format() {
        dao.add(new AResource(1));
        request.setResourceUri(resourceName);
        route.doGet(request, response);
        AResource[] resources = responseResources();
        assertEquals(1, resources.length);
    }

    @Test
    public void specify_input_content_format() {
        request.setResourceUri(resourceName);
        request.setBody(new XmlFormat().serialize(new AResource(123)));
        request.setContentType("application/xml");
        route.doPost(request, response);

        assertEquals(1, dao.resources().size());
        assertEquals(123, dao.resources().get(0).getId());
        assertEquals(resourceName + "/123", response.getCreatedResourceURI());
    }

    @Test
    public void specify_input_content_format_with_encoding() {
        request.setResourceUri(resourceName);
        request.setBody(new XmlFormat().serialize(new AResource(123)));
        request.setContentType("application/xml; charset=UTF-8");
        route.doPost(request, response);

        assertEquals(1, dao.resources().size());
        assertEquals(123, dao.resources().get(0).getId());
        assertEquals(resourceName + "/123", response.getCreatedResourceURI());
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
