package cl.blueprintsit.framework.liteclient;

import cl.blueprintsit.framework.datasources.DatasourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by BluePrints Developer on 02-12-2016.
 */

@ManagedBean(name = "liteClientBean")
@ViewScoped
public class LiteClientBean {

    final Logger LOGGER = LoggerFactory.getLogger(LiteClientBean.class);

    @EJB
    DatasourceManager datasourceManager;

    private String selectedDatasource;
    private String query;

    private boolean validQuery = false;

    private List<String> historial;



    private List<Object[]> resultRows;
    private List<String> columnNames;

    public List<Object[]> getResultRows() {
        return resultRows;
    }

    public void setResultRows(List<Object[]> resultRows) {
        this.resultRows = resultRows;
    }

    public String getSelectedDatasource() {
        return selectedDatasource;
    }

    public void setSelectedDatasource(String selectedDatasource) {
        this.selectedDatasource = selectedDatasource;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isValidQuery() {
        return validQuery;
    }

    public void setValidQuery(boolean validQuery) {
        this.validQuery = validQuery;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }


    /*
    ejecuta la query y marca el flag validQuery */
    public void callQuery(){

        validQuery = false;
        if(query==null || query.trim().equals("")) {
            showError("error", "ingrese query");
            return;
        }

        if(selectedDatasource==null  || selectedDatasource.trim().equals("")) {
            showError("error", "seleccione datasource");
            return;
        }
        Connection c=null;
        try {
            DataSource dataSource = datasourceManager.getDatasource(selectedDatasource);

            c = dataSource.getConnection();
            PreparedStatement statement = c.prepareCall(query);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet ==null) {
                showError("Error", "error al ejecutar query. rs null");
                return;
            }

            validQuery = true;
            resultRows = fetchResultRows(resultSet);
            columnNames = fetchColumnNames(resultSet);

            getHistorial().add(0,query);

            resultSet.close();

        }catch (SQLException e){
            showError("Error","error al ejecutar query. " +e.getMessage());
        }finally {
            if (c!=null)
                try {
                    c.close();
                } catch (SQLException e) {}
        }

    }

    public Set<String> getDataSourceNames() {
        return datasourceManager.getDatasourceNames();
    }


    public List<String> fetchColumnNames(ResultSet resultSet){
        List<String> list = new ArrayList<String>();
        try {
            if(validQuery){
                ResultSetMetaData rsmd = resultSet.getMetaData();
                for(int i = 1; i<=rsmd.getColumnCount();i++)
                   list.add(rsmd.getColumnName(i));

            }
        } catch (SQLException e) {}

        return list;


    }

    private List<Object[]> fetchResultRows(ResultSet resultSet){

        List<Object[]> list = new ArrayList<Object[]>();
        try {
            if(validQuery){
                int columnCount = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()){
                    Object[] row = new Object[columnCount];
                    for(int i = 1; i  <= columnCount; i++) {
                        Object object = resultSet.getObject(i);

                        if(object instanceof Clob){
                            object = convertStreamToString( ((Clob) object).getAsciiStream());
                        }

                        row[i-1] = object;
                    }
                    list.add(row);
                }

            }
        } catch (SQLException e) {}

        return list;

    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public List<String> getHistorial() {
        if(historial==null)
            historial=new ArrayList<String>();
        return historial;
    }

    public void setHistorial(List<String> historial) {
        this.historial = historial;
    }


    protected void showError(String title, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
    }

}
