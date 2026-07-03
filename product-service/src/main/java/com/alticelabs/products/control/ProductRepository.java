package com.alticelabs.products.control;

import com.alticelabs.products.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product,UUID> {
}
