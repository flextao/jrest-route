/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.rest.http;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flextao.rest.F;
import com.flextao.rest.ResourceController;
import com.flextao.rest.Routes;

/**
 * This servlet dispatchers request to your resource controller. You need
 * specify servlet init params for defining map of resource and resource
 * controller.
 * 
 * <pre>
 * For example, define a resource named "tasks" and it's controller:
 *  &lt;init-param&gt;
 *      &lt;param-name&gt;tasks&lt;/param-name&gt;
 *      &lt;param-value&gt;com.flextao.doit.task.resource.TaskResourceController&lt;/param-value&gt;
 *  &lt;/init-param&gt;
 * </pre>
 */
public class RestServlet extends HttpServlet {
    private static final long serialVersionUID = 1278162851671521752L;
    private final Routes route = new Routes();

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        for (Enumeration names = config.getInitParameterNames(); names.hasMoreElements();) {
            String name = names.nextElement().toString();
            String controller = config.getInitParameter(name);
            route.map().add(name, (Class<? extends ResourceController<?>>) F.forName(controller));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        route.doGet(new HttpResourceRequest(req), new HttpResourceResponse(req, resp));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        route.doPost(new HttpResourceRequest(req), new HttpResourceResponse(req, resp));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        route.doDelete(new HttpResourceRequest(req), new HttpResourceResponse(req, resp));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        route.doPut(new HttpResourceRequest(req), new HttpResourceResponse(req, resp));
    }

}
