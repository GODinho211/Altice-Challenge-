package com.alticelabs.categorias.boundary;


import com.alticelabs.categorias.control.CategoryService;
import com.alticelabs.categorias.entity.Category;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService service;

    @GET
    public List<Category> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Category getById(@PathParam("id") UUID id) {
        Category category = service.getById(id);

        if (category == null) {
            throw new NotFoundException("Category not found");
        }

        return category;
    }

    @POST
    public Category create(Category category) {
        return service.create(category);
    }

    @PUT
    @Path("/{id}")
    public Category update(@PathParam("id") UUID id, Category category) {
        Category updated = service.update(id, category);

        if (updated == null) {
            throw new NotFoundException("Category not found");
        }

        return updated;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
        boolean deleted = service.delete(id);

        if (!deleted) {
            throw new NotFoundException("Category not found");
        }
    }
}
