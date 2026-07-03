package org.alticelabs.client;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.alticelabs.cart.boundary.UserDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.UUID;

@Path("/user")
@RegisterRestClient(baseUri = "http://localhost:8083")
public interface UserClient {

    @GET
    @Path("/{id}")
    UserDto getUserById(@PathParam("id") UUID id);
}
