/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResourceInfoTest {
    @Test
    public void from_resource_uri() {
        assertResourceInfo("res", "id", ResourceInfo.from("res/id"));
        assertResourceInfo("res", "id", ResourceInfo.from("/res/id/"));
        assertResourceInfo("res", null, ResourceInfo.from("res"));
        assertResourceInfo("id", null, ResourceInfo.from("/id//"));
    }

    private void assertResourceInfo(String name, String id, ResourceInfo info) {
        assertEquals(name, info.getResourceName());
        assertEquals(id, info.getResourceId());
    }
}
