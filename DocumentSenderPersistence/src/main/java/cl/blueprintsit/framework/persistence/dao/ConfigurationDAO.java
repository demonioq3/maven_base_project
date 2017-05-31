package cl.blueprintsit.framework.persistence.dao;

import cl.blueprintsit.framework.domain.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
@Interceptors(value={net.bull.javamelody.MonitoringInterceptor.class})
public class ConfigurationDAO {

    static final Logger logger = LoggerFactory.getLogger(ConfigurationDAO.class);


    private static final String PARAMETER_CANT_BE_NULL = "Parameter can't be null";

    @PersistenceContext(unitName = "DocumentSenderPersistenceUnit")
    private EntityManager em;

    public Configuration getByKey(String key) {

        try {
            Configuration configuration = em.find(Configuration.class,key);
            em.detach(configuration);
            return configuration;
        }catch (NoResultException e){
            logger.error("Configuracion [{}] No encontrada",key, e);
        }
        catch (NonUniqueResultException e){
            logger.error("Configuracion [{}] duplicada",key, e);
        }

        return null;
    }

    public List<Configuration> findAll() {
        Query query = em.createQuery("select i from Configuration i ORDER BY i.llave asc");

        List<Configuration> resultList = query.getResultList();

        for (Configuration configuration : resultList) {
            em.detach(configuration);
        }

        List<Configuration> returnList = new ArrayList<Configuration>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Configuration create(Configuration configuration) throws ConfigurationExistsException{

        if ( configuration == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        if(getByKey(configuration.getLlave())!= null)
            throw new ConfigurationExistsException("Configuracion para esa llave ya existe");

        em.persist(configuration);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(configuration);
        return configuration;
    }

    public Configuration update(Configuration configuration) {

        if ( configuration == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Configuration updated = em.merge(configuration);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(Configuration configuration) {

        if ( configuration == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Configuration toDelete = em.find(Configuration.class,configuration.getLlave());
        em.remove(toDelete);

        return true;
    }

    public class ConfigurationExistsException extends Exception {
        public ConfigurationExistsException(String s) {
            super(s);
        }
    }
}