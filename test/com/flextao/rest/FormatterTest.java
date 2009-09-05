
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
