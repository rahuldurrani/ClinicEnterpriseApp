
package edu.stevens.cs548.clinic.service.web.soap.provider;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ProviderServiceExn", targetNamespace = "http://cs548.stevens.edu/clinic/service/web/soap")
public class ProviderServiceExn_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ProviderServiceExn faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ProviderServiceExn_Exception(String message, ProviderServiceExn faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ProviderServiceExn_Exception(String message, ProviderServiceExn faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: edu.stevens.cs548.clinic.service.web.soap.provider.ProviderServiceExn
     */
    public ProviderServiceExn getFaultInfo() {
        return faultInfo;
    }

}