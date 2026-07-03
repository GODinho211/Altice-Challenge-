package org.alticelabs.cart.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.alticelabs.cart.boundary.UserDto;
import org.alticelabs.cart.entity.Cart;
import org.alticelabs.cartItem.entity.CartItem;
import org.alticelabs.client.UserClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CartService {

    @Inject
    CartRepository repository;

    @Inject
    @RestClient
    UserClient userClient;

    public List<Cart> findAll() {
        return repository.listAll();
    }

    public Cart findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Cart create(Cart input) {

        UserDto user = userClient.getUserById(input.getUserId());

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        Cart cart = new Cart();
        cart.setUserId(input.getUserId());
        cart.setItems(new ArrayList<>());

        repository.persist(cart);

        return cart;
    }

    @Transactional
    public Cart update(UUID id, Cart cart) {

        Cart entity = repository.findById(id);

        if (entity == null) {
            return null;
        }

        UserDto user = userClient.getUserById(cart.getUserId());

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        entity.setUserId(cart.getUserId());
        entity.setItems(cart.getItems());

        return entity;
    }

    @Transactional
    public boolean delete(UUID id) {

        return repository.deleteById(id);
    }



}
