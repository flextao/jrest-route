/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.util.HashMap;
import java.util.Map;

public class ResourceMap {

    private Map<String, Class<? extends ResourceController<?>>> map = new HashMap<String, Class<? extends ResourceController<?>>>();

    public void add(String resourceName, Class<? extends ResourceController<?>> resourceClass) {
        map.put(resourceName, resourceClass);
    }

    public Class<? extends ResourceController<?>> get(String resourceName) {
        return map.get(resourceName);
    }

    public void remove(String resourceName) {
        map.remove(resourceName);
    }

    @Override
    public String toString() {
        return "Keys: " + F.join(map.keySet(), ", ");
    }
}
