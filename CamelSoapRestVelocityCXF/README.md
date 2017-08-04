CamelSoapRestVelocityCXF
========================
This example demos, 

--> Camel HTTP component consuming SOAP and Rest Svc with velocity.
--> Camel CXF component to consume and produce SOAP and Rest WebSvcs.
--> Generates a WebSvc(WSDL in target dir from java interface using Maven plugin).


To install amq
==============
features:install camel-jms
features:install camel-amq


URL's after deploying on Fuse
==============================
http://localhost:8181/cxf
http://localhost:8181/cxf/wsdlfrominterface/?wsdl
http://localhost:8181/cxf/restfrominterface/testget
http://localhost:8181/cxf/restfrominterface/testpost