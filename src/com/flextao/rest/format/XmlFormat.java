/******************************************************************************

* 杭州昼韬信息技术有限公司版权所有。

* 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
* 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

***********************************************************************/

package com.flextao.rest.format;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.flextao.rest.Format;

public class XmlFormat implements Format {

    @SuppressWarnings("unchecked")
    public <T> T deserialize(String str, Class<T> type) {
        XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(str.getBytes()));
        return (T) decoder.readObject();
    }

    public String serialize(Object obj) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(os));
        encoder.writeObject(obj);
        encoder.close();
        return os.toString();
    }

}
