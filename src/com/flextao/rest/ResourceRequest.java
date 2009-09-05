/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

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
