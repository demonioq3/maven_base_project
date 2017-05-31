package cl.blueprintsit.framework.config;

import cl.blueprintsit.framework.domain.Configuration;
import cl.blueprintsit.framework.persistence.dao.ConfigurationDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
@Stateless
@Interceptors(value={net.bull.javamelody.MonitoringInterceptor.class})
public class ConfigurationManager{


    @EJB
    ConfigurationDAO dao;


    public Configuration getByKey(String key) {
        return dao.getByKey(key);
    }

    public List<Configuration> findAll() {
        return dao.findAll();
    }

    public Configuration create(Configuration configuration) throws ConfigurationExistsException {

        try {
            return dao.create(configuration);
        } catch (ConfigurationDAO.ConfigurationExistsException e) {
            throw new ConfigurationExistsException(e);
        }
    }

    public Configuration update(Configuration configuration) {
        return dao.update(configuration);
    }

    public void delete(Configuration configuration) {
        dao.delete(configuration);
    }

    public class ConfigurationExistsException extends Exception {
        public ConfigurationExistsException() {
        }

        public ConfigurationExistsException(String message) {
            super(message);
        }

        public ConfigurationExistsException(String message, Throwable cause) {
            super(message, cause);
        }

        public ConfigurationExistsException(Throwable cause) {
            super(cause);
        }
    }
}
