/**
 * ServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public interface ServiceSoap_PortType extends java.rmi.Remote {

    /**
     * [devuelve un xmldocument]
     */
    public cl.metlife.ws.clients.auth.Autentifica_InternoResponseAutentifica_InternoResult autentifica_Interno(java.lang.String ID_Aplicacion, java.lang.String login, java.lang.String clave) throws java.rmi.RemoteException;

    /**
     * [devuelve un xmldocument]
     */
    public cl.metlife.ws.clients.auth.ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult ROLES_PERSONA_RUT_APLIC(java.lang.String ID_Aplicacion, java.lang.String login) throws java.rmi.RemoteException;

    /**
     * [devuelve un String]
     */
    public cl.metlife.ws.clients.auth.LOGINResponseLOGINResult LOGIN(java.lang.String vl_login, java.lang.String clave) throws java.rmi.RemoteException;

    /**
     * [devuelve un String]
     */
    public cl.metlife.ws.clients.auth.Check_claveResponseCheck_claveResult check_clave(java.lang.String inrut, java.lang.String icclave, java.lang.String icsession, java.lang.String icidioma) throws java.rmi.RemoteException;

    /**
     * [Datos persona]
     */
    public cl.metlife.ws.clients.auth.Sp_info_personaResponseSp_info_personaResult sp_info_persona(java.lang.String inrut) throws java.rmi.RemoteException;
}
