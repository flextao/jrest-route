/******************************************************************************

* 杭州昼韬信息技术有限公司版权所有。

* 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
* 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

***********************************************************************/

package com.flextao.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class FTest {
    @Test
    public void parseContentType() {
        assertNull(F.parseCharsetFromContentType("text/html"));
        assertNull(F.parseCharsetFromContentType(""));
        assertNull(F.parseCharsetFromContentType(null));
        assertEquals("UTF-8", F.parseCharsetFromContentType("text/html; charset=UTF-8"));
        assertEquals("UTF-8", F.parseCharsetFromContentType("text/html; Charset=UTF-8"));
    }
}
