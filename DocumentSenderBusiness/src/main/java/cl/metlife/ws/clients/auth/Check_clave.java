/**
 * Check_clave.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class Check_clave  implements java.io.Serializable {
    private java.lang.String inrut;

    private java.lang.String icclave;

    private java.lang.String icsession;

    private java.lang.String icidioma;

    public Check_clave() {
    }

    public Check_clave(
           java.lang.String inrut,
           java.lang.String icclave,
           java.lang.String icsession,
           java.lang.String icidioma) {
           this.inrut = inrut;
           this.icclave = icclave;
           this.icsession = icsession;
           this.icidioma = icidioma;
    }


    /**
     * Gets the inrut value for this Check_clave.
     * 
     * @return inrut
     */
    public java.lang.String getInrut() {
        return inrut;
    }


    /**
     * Sets the inrut value for this Check_clave.
     * 
     * @param inrut
     */
    public void setInrut(java.lang.String inrut) {
        this.inrut = inrut;
    }


    /**
     * Gets the icclave value for this Check_clave.
     * 
     * @return icclave
     */
    public java.lang.String getIcclave() {
        return icclave;
    }


    /**
     * Sets the icclave value for this Check_clave.
     * 
     * @param icclave
     */
    public void setIcclave(java.lang.String icclave) {
        this.icclave = icclave;
    }


    /**
     * Gets the icsession value for this Check_clave.
     * 
     * @return icsession
     */
    public java.lang.String getIcsession() {
        return icsession;
    }


    /**
     * Sets the icsession value for this Check_clave.
     * 
     * @param icsession
     */
    public void setIcsession(java.lang.String icsession) {
        this.icsession = icsession;
    }


    /**
     * Gets the icidioma value for this Check_clave.
     * 
     * @return icidioma
     */
    public java.lang.String getIcidioma() {
        return icidioma;
    }


    /**
     * Sets the icidioma value for this Check_clave.
     * 
     * @param icidioma
     */
    public void setIcidioma(java.lang.String icidioma) {
        this.icidioma = icidioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Check_clave)) return false;
        Check_clave other = (Check_clave) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.inrut==null && other.getInrut()==null) || 
             (this.inrut!=null &&
              this.inrut.equals(other.getInrut()))) &&
            ((this.icclave==null && other.getIcclave()==null) || 
             (this.icclave!=null &&
              this.icclave.equals(other.getIcclave()))) &&
            ((this.icsession==null && other.getIcsession()==null) || 
             (this.icsession!=null &&
              this.icsession.equals(other.getIcsession()))) &&
            ((this.icidioma==null && other.getIcidioma()==null) || 
             (this.icidioma!=null &&
              this.icidioma.equals(other.getIcidioma())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getInrut() != null) {
            _hashCode += getInrut().hashCode();
        }
        if (getIcclave() != null) {
            _hashCode += getIcclave().hashCode();
        }
        if (getIcsession() != null) {
            _hashCode += getIcsession().hashCode();
        }
        if (getIcidioma() != null) {
            _hashCode += getIcidioma().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Check_clave.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">check_clave"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inrut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "inrut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icclave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "icclave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icsession");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "icsession"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icidioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "icidioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
