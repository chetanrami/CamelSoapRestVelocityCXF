package com.chetan.examples.routes.velocity;

import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crami on 07/28/2017.
 */
public class VelocityRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:chetan_velocity").routeId("velocity")
                .to("velocity:TEMPLATE_NAME_IN_HEADER?propertiesFile=Velocity/velocity.properties");
        from("direct:chetan_velocityXmlEscape").routeId("velocityXmlEscape")
                .to("velocity:TEMPLATE_NAME_IN_HEADER?propertiesFile=Velocity/velocityXmlEscape.properties");
    }
}