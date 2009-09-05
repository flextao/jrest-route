/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest;

import java.util.Arrays;
import java.util.List;

public class AResourceController extends ResourceController<AResource> {

    private AResourceDao dao = AResourceDao.getInstance();

    @Override
    public List<AResource> list() {
        String id = getRequest().getParams().get("id");
        if (id != null) {
            return Arrays.asList(dao.findResourceById(id));
        } else {
            return dao.resources();
        }
    }

    @Override
    public String create() {
        AResource res = parse(AResource.class);
        dao.add(res);
        return String.valueOf(res.getId());
    }

    @Override
    public AResource show(String resourceId) {
        return dao.findResourceById(resourceId);
    }

    @Override
    public AResource update(String resourceId) {
        AResource res = dao.findResourceById(resourceId);
        if (res == null) {
            return null;
        }
        AResource updated = parse(AResource.class);
        res.setName(updated.getName());
        return res;
    }

    @Override
    public AResource destroy(String resourceId) {
        AResource res = dao.findResourceById(resourceId);
        dao.resources().remove(res);
        return res;
    }

}
