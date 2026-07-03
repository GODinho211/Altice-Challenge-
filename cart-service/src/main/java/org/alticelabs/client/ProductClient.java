package org.alticelabs.client;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.alticelabs.cart.boundary.ProductDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.UUID;

@Path("/products")
@RegisterRestClient(baseUri = "http://localhost:8080")
public interface ProductClient {

    @GET
    @Path("/{id}")
    ProductDto getProductById(@PathParam("id") UUID id);
}
