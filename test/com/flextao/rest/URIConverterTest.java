/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class URIConverterTest {

    @Test
    public void resourceURI() {
        URIConverter.getInstance().setResourcePrefix("resources");
        String result = URIConverter.getInstance().resourceURI("/doit", "/doit/resources/task");
        assertEquals("task", result);
    }
}
