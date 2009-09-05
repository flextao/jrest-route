/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import com.google.gson.Gson;

public class JsonFormat implements Format {

    private final Gson gson;

    public JsonFormat(Gson gson) {
        this.gson = gson;
    }

    public <T> T deserialize(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public String serialize(Object obj) {
        return gson.toJson(obj);
    }

}
