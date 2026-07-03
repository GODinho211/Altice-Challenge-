package org.alticelabs.analytics.boundary;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.alticelabs.analytics.control.AnalyticsService;

import java.util.Map;
import java.util.UUID;

@Path("/analytics")
@Produces(MediaType.APPLICATION_JSON)
public class AnalyticsResource {

    @Inject
    AnalyticsService service;

    @GET
    @Path("/carts-with-items")
    public long cartsWithItems() {
        return service.cartsWithItems();
    }

    @GET
    @Path("/items/max")
    public int maxItems() {
        return service.maxItems();
    }

    @GET
    @Path("/items/min")
    public int minItems() {
        return service.minItems();
    }

    @GET
    @Path("/items/average")
    public double averageItems() {
        return service.averageItems();
    }

    @GET
    @Path("/top-items")
    public Map<UUID, Integer> topItems() {
        return service.topItems();
    }
}
