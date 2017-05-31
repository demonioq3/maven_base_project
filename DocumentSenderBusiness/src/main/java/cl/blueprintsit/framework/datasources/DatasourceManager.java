package cl.blueprintsit.framework.datasources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import javax.naming.Binding;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Singleton
@Interceptors(value={net.bull.javamelody.MonitoringInterceptor.class})
public class DatasourceManager {

    final Logger logger = LoggerFactory.getLogger(DatasourceManager.class);

    InitialContext initialContext;
    private Map<String,DataSource> dataSourceMap;

    /*
     Busca en el contexto todos los objetos de la clase datasource y los llena en un mapa
      */
    private Map<String,DataSource> getDatasourceMap(){

        if(dataSourceMap == null) {

            dataSourceMap = new HashMap<String, DataSource>();
            try {
                if (initialContext == null) {
                    initialContext = new InitialContext();
                }

                fetchBindingsByPattern("");
                fetchBindingsByPattern("jdbc");

            } catch (NamingException ex) {
                logger.error("Error al buscar nombres de datasources", ex);
            }
        }
        return dataSourceMap;

    }

    private void fetchBindingsByPattern(String pattern) throws NamingException {
        NamingEnumeration<Binding> list = initialContext.listBindings(pattern);
        while (list.hasMore()) {

            Binding binding = list.next();
            if(binding.getObject() instanceof DataSource)
                dataSourceMap.put(binding.getName(), (DataSource)binding.getObject());
        }
    }


    public DataSource getDatasource(String datasourceName) {
        DataSource dataSource = getDatasourceMap().get(datasourceName);
        return net.bull.javamelody.JdbcWrapper.SINGLETON.createDataSourceProxy(dataSource);
    }

    public Set<String> getDatasourceNames() {
        return getDatasourceMap().keySet();
    }
}
