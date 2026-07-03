package org.alticelabs.cartItem.control;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.alticelabs.cartItem.entity.CartItem;

import java.util.UUID;

@ApplicationScoped
public class CartItemRepository implements PanacheRepositoryBase<CartItem, UUID> {
}
