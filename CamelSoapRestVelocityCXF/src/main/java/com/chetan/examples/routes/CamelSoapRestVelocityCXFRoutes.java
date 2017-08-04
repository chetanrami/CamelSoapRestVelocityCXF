package com.chetan.examples.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.message.MessageContentsList;

public class CamelSoapRestVelocityCXFRoutes extends RouteBuilder {

    private static final String LOGGER = "com.chetan.examples.routes.CamelSoapRestVelocityCXFRoutes_outbound";

    public static final String DIRECT_GET_REST = "direct:getRest";
    public static final String DIRECT_POST_REST = "direct:postRest";

    private static final String XPATH_VALUE =
            "/*[name()='soap:Envelope']/*[name()='soap:Body']/*[name()='web:GetCitiesByCountryResponse']/*[name()='web:GetCitiesByCountryResult']/text()";

    @Override
    public void configure() throws Exception {

        from("cxf:bean:cXFTest").to("processor");

//        irshad cxf soap
        from("cxf:bean:wsdlFromInterfaceEndpoint")
                .process(new Processor(){
                    public void process(Exchange e) throws Exception {
                        MessageContentsList list = (MessageContentsList) e.getIn().getBody();
                        e.getIn().setBody(list.get(0));
                    }
                }).recipientList(simple("direct:${header.operationName}"));

        from("direct:getRespWebMethodOper")
                .setBody(constant("getRespWebMethodOper"));

        from("direct:getRespWebMethodOper2")
                .setBody(constant("getRespWebMethodOper2"));

//        irshad cxf Rest
        from("cxfrs:bean:restFromInterfaceEndpoint")
                .recipientList(simple("direct:${header.operationName}"))
                .setHeader(Exchange.CONTENT_LENGTH, simple("-1"));

        from("direct:testGet")
                .setBody(constant("cameToTestGet"));

        from("direct:testPost")
                .setBody(constant("cameToTestPost"));







        restConfiguration()
                .component("servlet")
                .endpointProperty("servletName", "camelServletJavaTestProj");

        rest("/getRest/").get("/{token}")
                .route()
                .from(DIRECT_GET_REST).routeId("getRestRouteID")
                .setBody(simple("${in.header.token}")).id("setRestBody")
                .log(LoggingLevel.INFO, LOGGER, "Logging http token sent: ${body}")
                .setBody(simple("Hello you stepped into rest svc get route with token: ${body}"))
                .log(LoggingLevel.INFO, LOGGER, "${body}")
                .end();

        rest("/postRest/").post()
                .route()
                .from(DIRECT_POST_REST).routeId("postRestRouteID")
                .setBody(simple("Hello you stepped into rest svc post route with sent body: ${body}"))
                .log(LoggingLevel.INFO, LOGGER, "getPost body:----- ${body}")
                .end();

        from("activemq:queue:{{transactionsXMLReadingQ}}")
                .routeId("activeMQConsumeFromQ_RouteID")
                .log(LoggingLevel.INFO, LOGGER, "Read this body from Q: ${body}")

                .setHeader("CamelVelocityResourceUri", constant("Velocity/SoapBody.vm"))
                .to("direct:chetan_velocity")//.to("direct:chetan_velocityXmlEscape")
                .setHeader("CamelVelocityResourceUri", constant("Velocity/SoapEnvelope.vm"))
                .to("direct:chetan_velocity")//.to("direct:chetan_velocityXmlEscape")
                .log(LoggingLevel.INFO, LOGGER, "Logging in velocity after escaping: ==> ${body}")

                .setHeader("SOAPAction", constant("http://www.webserviceX.NET/GetCitiesByCountry"))
                .setHeader("Content-Type", constant("text/xml;charset=UTF-8"))

                .to("http://localhost:8080?httpClient.soTimeout=250000")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, LOGGER, "Raw response from SOAP call: ${body}")
                .setBody(xpath(XPATH_VALUE))
                .log(LoggingLevel.INFO, LOGGER, "XPath Value from response: ${body}")

                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to("http://localhost:8089/testrest")
                .log(LoggingLevel.INFO, LOGGER, "response from rest call: ${body}")

                .end();
    }
}