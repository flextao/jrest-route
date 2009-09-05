/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    public static String formateDate(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        return formater.format(date);
    }

    public static Date parseDate(String date) {
        if (date == null) {
            return null;
        }
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        try {
            return formater.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Couldn't parse date string: " + date, e);
        }
    }
}
