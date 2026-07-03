package com.alticelabs.products.boundary;

import com.alticelabs.products.entity.Product;
import com.alticelabs.products.control.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource{

    @Inject
    ProductService service;

    @GET
    public List<Product> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") UUID id) {
        return service.getById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @POST
    public ProductResponseDto create(ProductRequestDto product) {
        return service.create(product);
    }

    @PUT
    @Path("/{id}")
    public Product update(@PathParam("id") UUID id, Product product) {
        Product updated = service.update(id, product);

        if (updated == null) {
            throw new NotFoundException("Product not found");
        }

        return updated;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
        boolean deleted = service.delete(id);

        if (!deleted) {
            throw new NotFoundException("Product not found");
        }
    }
}