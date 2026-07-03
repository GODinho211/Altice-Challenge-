package org.alticelabs.user.boundary;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.alticelabs.user.control.UserService;
import org.alticelabs.user.entity.User;

import java.util.List;
import java.util.UUID;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    UserService service;

    @GET
    public List<User> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") UUID id) {
        return service.getById(id);
    }

    @POST
    public User create(UserRequest req) {
        return service.create(req);
    }

    @PUT
    @Path("/{id}")
    public User update(@PathParam("id") UUID id, UserRequest req) {
        return service.update(id, req);
    }

    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") UUID id) {
        return service.delete(id);
    }

    @GET
    @Path("/active")
    public List<User> active() {
        return service.activeUsers();
    }

}
