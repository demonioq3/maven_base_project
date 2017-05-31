package cl.blueprintsit.framework.auth;

import cl.blueprintsit.framework.Constants;
import cl.blueprintsit.framework.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
@ManagedBean(name = "authenticationBean")
@SessionScoped
public class AuthenticationBean {

    public static final String AUTH_KEY = "bp.session.user";

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationBean.class);



    @EJB(name = "AuthenticationManagerEJB")
    private AuthenticationManager authenticationManager;

    @EJB(name = "AuthorizationManagerEJB")
    private AuthorizationManager authorizationManager;

    @EJB
    private ConfigurationManager configuration;

    private String username;
    private String password;

    private User loggedUser;

    public void checkForGroup(String groupname) throws AuthorizationException {
        for (Group group : getLoggedUser().getGroups()) {
            if (groupname.equals(group.getName()))
                return;
        }
        throw new AuthorizationException("Usuario no pertenece al grupo "+groupname);
    }


    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_KEY) != null;
    }


    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
    }


    public void login() {
        try {
            //valida user y pass
            authenticationManager.authenticate(username,password);

            //quitar password de la memoria
            password=null;

            //poner datos de usuario en sesion
            loggedUser = authorizationManager.getUser(username);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.getSessionMap().put(AUTH_KEY, username);

            //marcar logueo
            authorizationManager.markLogin(username);

            //redirigir a pagina de inicio
            context.redirect(context.getRequestContextPath() + Constants.HOME_PAGE);

            logger.info("Usuario [{}] ha iniciado sesión.",username);


        } catch (AuthenticationException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Datos Incorrectos!", "Nombre de usuario y/o contraseña incorrecta."));
            logger.error("Error trying to login", e);
        }
        catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error intentando redirigir usuario a página de Inicio.", e.getMessage()));
            logger.error("Error intentando redirigir usuario a página de inicio {}", Constants.HOME_PAGE , e);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Ingresar!", e.getMessage()));
            logger.error("Error trying to login", e);

        }
    }

    public void logout() {
        logger.info("Usuario: [{}] ha cerrado su sesión.",username);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getSessionMap().remove(AUTH_KEY);

        username = null;
        password = null;
        loggedUser = null;

        try {
            context.redirect(context.getRequestContextPath() + "/" +Constants.VIEWS_FOLDER+ "/" + Constants.LOGIN_PAGE );
        } catch (IOException e) {
            logger.error("Error en logout", e);
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }



}
