package com.chetan.examples.servicefromInterface;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@WebService
public interface RestFromInterface {

    @GET
    @Path("/testget")
    String testGet();

    @POST
    @Path("/testpost")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    String testPost(String passingArg);

}
