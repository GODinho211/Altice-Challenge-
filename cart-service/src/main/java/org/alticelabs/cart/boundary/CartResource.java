package org.alticelabs.cart.boundary;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.alticelabs.cart.control.CartService;
import org.alticelabs.cart.control.CheckoutService;
import org.alticelabs.cart.entity.Cart;
import org.alticelabs.cartItem.control.CartItemService;
import org.alticelabs.cartItem.entity.CartItem;

import java.util.List;
import java.util.UUID;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartService service;

    @Inject
    CartItemService itemService;

    @Inject
    CheckoutService checkoutService;

    @GET
    public List<Cart> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {

        Cart cart = service.findById(id);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(cart).build();
    }

    @POST
    public Response create(Cart cart) {

        Cart created = service.create(cart);

        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, Cart cart) {

        Cart updated = service.update(id, cart);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {

        boolean deleted = service.delete(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }


    @POST
    @Path("/{cartId}/items")
    public Response addItem(
            @PathParam("cartId") UUID cartId,
            CartItem item
    ) {
        Cart updated = itemService.addItem(cartId, item);

        return Response.ok(updated).build();
    }

    @PUT
    @Path("/{cartId}/items/{productId}")
    public Response updateItem(
            @PathParam("cartId") UUID cartId,
            @PathParam("productId") UUID productId,
            CartItem item
    ) {
        Cart updated = itemService.updateItem(cartId, productId, item);

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{cartId}/items/{productId}")
    public Response removeItem(
            @PathParam("cartId") UUID cartId,
            @PathParam("productId") UUID productId
    ) {
        Cart updated = itemService.removeItem(cartId, productId);

        return Response.ok(updated).build();
    }

    @POST
    @Path("/{cartId}/checkout")
    public Response checkout(@PathParam("cartId") UUID cartId) {

        CheckoutResponse response = checkoutService.checkout(cartId);

        return Response.ok(response).build();
    }

}
