/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

public class FormatterTest {

    @Test
    public void formateDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String date = Formatter.formateDate(F.date(2000, 1, 1));
        assertEquals("2000-02-01 00:00:00 +0000", date);
        assertNull(Formatter.formateDate(null));
    }

    @Test
    public void parseDate() {
        assertNull(Formatter.parseDate(null));
        Date date = Formatter.parseDate("2000-02-01 00:00:00 +0000");
        assertEquals(F.date(2000, 1, 1), date);
    }
}
