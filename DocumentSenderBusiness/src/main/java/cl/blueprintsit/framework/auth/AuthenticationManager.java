package cl.blueprintsit.framework.auth;

import cl.metlife.auth.MetlifeAuthenticationMethod;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
@Stateless
@Interceptors(value={net.bull.javamelody.MonitoringInterceptor.class})
public class AuthenticationManager {

    @EJB
    MetlifeAuthenticationMethod metlifeAuthenticationMethod;

    @EJB
    DummyAuthenticationMethod dummyAuthenticationBean;

    @EJB
    TableAuthenticationMethod tableAuthenticationMethod;

    public void authenticate(String username, String password) throws AuthenticationException {
         getAuthenticationMethod().authenticate(username,password);
    }


    private AuthenticationMethod getAuthenticationMethod(){
        return dummyAuthenticationBean;

    }
}
