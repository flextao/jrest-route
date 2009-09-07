
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
import com.flextao.rest.ResourceRequest;
import com.flextao.rest.Routes;

/**
 * This servlet dispatchers request to your resource controller. You need
 * specify servlet init params for defining map of resource and resource
 * controller.
 * 
 * <pre>
 * For example, define a resource &quot;tasks&quot; and it's controller:
 *  &lt;init-param&gt;
 *      &lt;param-name&gt;tasks&lt;/param-name&gt;
 *      &lt;param-value&gt;com.flextao.doit.task.resource.TaskResourceController&lt;/param-value&gt;
 *  &lt;/init-param&gt;
 * </pre>
 */
public class RestServlet extends HttpServlet {
    private static final long serialVersionUID = 1278162851671521752L;
    private final Routes routes = new Routes();

    protected Routes getRoutes() {
        return routes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        for (Enumeration names = config.getInitParameterNames(); names.hasMoreElements();) {
            String name = names.nextElement().toString();
            String controller = config.getInitParameter(name);
            routes.getResourceMap().add(name, (Class<? extends ResourceController<?>>) F.forName(controller));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        routes.doGet(asResourceRequest(req), asResourceResponse(req, resp));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        routes.doPost(asResourceRequest(req), asResourceResponse(req, resp));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        routes.doDelete(asResourceRequest(req), asResourceResponse(req, resp));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        routes.doPut(asResourceRequest(req), asResourceResponse(req, resp));
    }

    private HttpResourceResponse asResourceResponse(HttpServletRequest req, HttpServletResponse resp) {
        return new HttpResourceResponse(req, resp, routes.getURIConverter());
    }

    private ResourceRequest asResourceRequest(HttpServletRequest req) {
        return new HttpResourceRequest(req, routes.getURIConverter());
    }
}
