package com.chetan.examples.servicefromInterface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "https://servicefromInterface.examples.chetan.com/", name = "wsdlfrominterface", serviceName = "wsdlfrominterface")
public interface WsdlFromInterface {

    @WebMethod(operationName = "getRespWebMethodOper")
    @WebResult(name = "getRespWebMethodOper")
    String getResponse(@WebParam(name = "requestArgument") String request);

    @WebMethod(operationName = "getRespWebMethodOper2")
    @WebResult(name = "getRespWebMethodOper2")
    String getResponse2(@WebParam(name = "requestArgument2") String request);
}
