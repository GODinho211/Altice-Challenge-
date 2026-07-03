package org.alticelabs.cart.control;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.alticelabs.cart.entity.Cart;

import java.util.UUID;

@ApplicationScoped
public class CartRepository implements PanacheRepositoryBase<Cart,UUID> {
}
