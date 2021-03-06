/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest.format;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flextao.rest.Format;

public class FormatsTest {

    @Test
    public void xml_format() {
        Format format = new XmlFormat();
        String str = format.serialize("object");
        assertEquals("object", format.deserialize(str, String.class));
    }

    @Test
    public void json_format() {
        Format format = new JsonFormat();
        String str = format.serialize("object");
        assertEquals("object", format.deserialize(str, String.class));
    }
}
