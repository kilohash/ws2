
package com.convertspeed.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConvertSpeedsHttpGet", targetNamespace = "http://www.webserviceX.NET/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConvertSpeedsHttpGet {


    /**
     * 
     * @param toUnit
     * @param speed
     * @param fromUnit
     * @return
     *     returns double
     */
    @WebMethod(operationName = "ConvertSpeed")
    @WebResult(name = "double", targetNamespace = "http://www.webserviceX.NET/", partName = "Body")
    public double convertSpeed(
            @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "speed")
                    String speed,
            @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "FromUnit")
                    String fromUnit,
            @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "ToUnit")
                    String toUnit);

}
