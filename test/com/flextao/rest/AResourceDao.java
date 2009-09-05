/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.util.ArrayList;
import java.util.List;

public class AResourceDao {
    private static AResourceDao instance = new AResourceDao();

    public static AResourceDao getInstance() {
        return instance;
    }

    private List<AResource> resources = new ArrayList<AResource>();

    public void add(AResource resource) {
        resources.add(resource);
    }

    public List<AResource> resources() {
        return resources;
    }

    public void clear() {
        resources.clear();
    }

    public AResource findResourceById(String resourceId) {
        int theId = Integer.parseInt(resourceId);
        for (AResource res : resources) {
            if (res.getId() == theId) {
                return res;
            }
        }
        return null;
    }

}
