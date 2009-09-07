package com.flextao.rest;

public interface ResourceRequest {
    /**
     * The default content type used for parse input content when there is no
     * content type specified
     */
    static String DEFAULT_CONTENT_TYPE = "json";

    /**
     * Resource URI is a relative URI, which should only include resource name
     * to identify a resource list and include resource name and id to identify
     * a resource item.
     * 
     * <pre>
     * For example:
     *   resource/id
     *   task/id
     *   money
     * </pre>
     */
    String getResourceURI();

    /**
     * @return parameter value
     */
    String getParameter(String name);

    /**
     * @return parameter values
     */
    String[] getParameterValues(String name);

    /**
     * @return the input content, it's probably from an input stream
     */
    String getInputContent();

    /**
     * @return the type of resource content returned from method getInputContent
     */
    String getContentType();

}
