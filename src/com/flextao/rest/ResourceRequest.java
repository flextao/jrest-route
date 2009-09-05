
package com.flextao.rest;

import java.util.Map;

public interface ResourceRequest {

    /**
     * Resource URI is a relative URI, which should only include resource name
     * to identify a resource list and include resource name and id to identify
     * a resource item.
     * <pre>
     * For example:
     *   resource/id
     *   task/id
     *   money
     * </pre>
     */
    String getResourceURI();

    /**
     * @return original request parameters.
     */
    Map<String, String> getParams();

    /**
     * @return the input content, it's probably from an input stream
     */
    String getInputContent();

}
