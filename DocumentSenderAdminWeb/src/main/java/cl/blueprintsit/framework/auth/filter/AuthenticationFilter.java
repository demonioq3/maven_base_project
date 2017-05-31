package cl.blueprintsit.framework.auth.filter;

import cl.blueprintsit.framework.Constants;
import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.blueprintsit.framework.auth.User;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
public class AuthenticationFilter implements Filter{


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (  req.getRequestURI().contains(Constants.LOGIN_PAGE) || req.getRequestURI().contains(Constants.ERRORS_FOLDER) || isvalidSession(req)) {
            chain.doFilter(request, response);
        } else if (!isLoggedIn(req)) { //perdio la sesion
            res.sendRedirect(req.getContextPath() +"/" + Constants.VIEWS_FOLDER+ "/" + Constants.LOGIN_PAGE + "?viewExpired=true&originalURI="+req.getRequestURI());
        } else if(!isvalidSession(req)) {//no tiene permiso para acceder a la pagina solicitada
            res.sendRedirect(req.getContextPath() +"/" + Constants.VIEWS_FOLDER+ "/" + Constants.ERRORS_FOLDER+ "/" + Constants.AUTH_ERROR_PAGE );
        } else {//otros casos que nunca deberian darse
            res.sendRedirect(req.getContextPath() + "/" + Constants.VIEWS_FOLDER+ "/"  + Constants.LOGIN_PAGE );
        }

    }

    private boolean isLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute(AuthenticationBean.AUTH_KEY) != null;
    }

    private boolean isvalidSession(HttpServletRequest req) {
        if ( !isLoggedIn(req) )
            return false;

        AuthenticationBean auth = (AuthenticationBean) req.getSession().getAttribute("authenticationBean");
        User u = auth.getLoggedUser();

        return u != null;

    }

    @Override
    public void destroy() {
        // Do nothing because no destroy logic
    }


    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Do nothing because no init logic
    }


}


