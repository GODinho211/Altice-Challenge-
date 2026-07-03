package org.alticelabs.cartItem.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.alticelabs.cart.boundary.ProductDto;
import org.alticelabs.cart.control.CartRepository;
import org.alticelabs.cart.entity.Cart;
import org.alticelabs.cartItem.entity.CartItem;
import org.alticelabs.client.ProductClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;


import java.util.UUID;

@ApplicationScoped
public class CartItemService {

    @Inject
    CartRepository cartRepository;

    @Inject
    @RestClient
    ProductClient productClient;

    @Transactional
    public Cart addItem(UUID cartId, CartItem item) {

        Cart cart = cartRepository.findById(cartId);

        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }

        //CALL PRODUCT SERVICE
        ProductDto product = productClient.getProductById(item.getProductId());

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        if (!product.available) {
            throw new BadRequestException("Product is not available");
        }


        // verificar se produto já existe no carrinho
        for (CartItem existing : cart.getItems()) {
            if (existing.getProductId().equals(item.getProductId())) {
                existing.setQuantity(
                        existing.getQuantity() + item.getQuantity()
                );
                return cart;
            }
        }

        item.setCart(cart);
        cart.getItems().add(item);

        return cart;
    }

    @Transactional
    public Cart updateItem(UUID cartId, UUID productId, CartItem item) {

        Cart cart = cartRepository.findById(cartId);

        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }

        ProductDto product = productClient.getProductById(productId);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        if (!product.available) {
            throw new BadRequestException("Product is not available");
        }

        for (CartItem existing : cart.getItems()) {
            if (existing.getProductId().equals(productId)) {
                existing.setQuantity(item.getQuantity());
                return cart;
            }
        }

        throw new NotFoundException("Product not found in cart");
    }

    @Transactional
    public Cart removeItem(UUID cartId, UUID productId) {

        Cart cart = cartRepository.findById(cartId);

        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }

        boolean removed = cart.getItems().removeIf(
                item -> item.getProductId().equals(productId)
        );

        if (!removed) {
            throw new NotFoundException("Product not found in cart");
        }

        return cart;
    }

}
