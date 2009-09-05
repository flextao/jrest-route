/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;


public class TestResourceResponse implements ResourceResponse {

    private StringBuffer body = new StringBuffer();
    private String createdResourceURI;

    public String body() {
        return this.body.toString();
    }

    public void write(String result) {
        this.body.append(result);
    }

    public String getCreatedResourceURI() {
        return createdResourceURI;
    }

    public void createdResourceURI(String uri) {
        createdResourceURI = uri;
    }

}
