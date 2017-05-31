package cl.blueprintsit.framework.beans;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.blueprintsit.framework.domain.Configuration;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Blueprints on 1/27/2016.
 */
@ManagedBean(name="configuracionesBean")
@ViewScoped
public class ConfiguracionesBean extends BaseBean{

    private Configuration selected;

    @EJB
    private ConfigurationManager manager;


    public void save(){
        try{
            manager.update(selected);
            showInfo("Aviso", "La configuración se editó correctamente");
        } catch (Exception e){
            showError("Error", "Hubo un error al crear/editar la configuracion");
        }

        selected = null;
    }

    public void edit(Configuration configuracion){
        selected = configuracion;
    }

    public List<Configuration> getAllConfiguraciones(){
        return manager.findAll();
    }

    public void newConfiguration(){
        selected = new Configuration();
    }

    public Configuration getSelected() {
        return selected;
    }

    public void setSelected(Configuration selected) {
        this.selected = selected;
    }
}
