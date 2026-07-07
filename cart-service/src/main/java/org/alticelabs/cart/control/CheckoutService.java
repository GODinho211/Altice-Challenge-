package org.alticelabs.cart.control;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.alticelabs.cart.boundary.CheckoutResponse;
import org.alticelabs.cart.entity.Cart;
import org.alticelabs.cartItem.entity.CartItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class CheckoutService {
    @Inject
    CartRepository cartRepository;

    @Transactional
    public CheckoutResponse checkout(UUID cartId) {

        Cart cart = cartRepository.findById(cartId);

        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }

        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        BigDecimal total = BigDecimal.ZERO;
        int totalItems = 0;

        for (CartItem item : cart.getItems()) {

            total = total.add(
                    item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
            );

            totalItems += item.getQuantity();
        }

        CheckoutResponse response = new CheckoutResponse();
        response.setCartId(cart.getId());
        response.setUserId(cart.getUserId());
        response.setTotal(total);
        response.setTotalItems(totalItems);
        response.setCheckoutDate(LocalDateTime.now());

        return response;
    }
}
