package org.example.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.services.MyService;

@Path("resources")
@Stateless
public class MyResource {

    @Inject
    MyService myService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEntity(@PathParam("id") Long id) {
        MyEntity entity = myService.find(id);
        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEntity(MyEntity myEntity) {
        try {
            MyEntity saved = myService.insert(myEntity);
            return Response.ok(saved).build();
        } catch (Exception e) {
            System.out.println("Exception in POST: ");
            System.out.println(e.getClass().getName());
            throw e;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEntity(MyEntity myEntity) {
        try {
            MyEntity saved = myService.update(myEntity);
            return Response.ok(saved).build();
        } catch (Exception e) {
            System.out.println("Exception in PUT: ");
            System.out.println(e.getClass().getName());
            throw e;
        }
    }
}
