package com.chetan.examples.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.12
 * 2017-08-04T16:35:18.263-05:00
 * Generated source version: 3.1.12
 * 
 */
@WebService(targetNamespace = "http://service.examples.chetan.com", name = "SOATestEndpoint")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SOATestEndpoint {

    @WebMethod(operationName = "SOATest", action = "http://service.examples.chetan.com")
    @WebResult(name = "outputSOATest", targetNamespace = "http://service.examples.chetan.com", partName = "out")
    public OutputSOATest soaTest(
        @WebParam(partName = "in", name = "inputSOATest", targetNamespace = "http://service.examples.chetan.com")
        InputSOATest in
    );
}
