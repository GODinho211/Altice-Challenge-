package com.alticelabs.products.boundary;

import com.alticelabs.categorias.entity.Category;
import com.alticelabs.products.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDto(
        String name,
        BigDecimal price,
        boolean available,
        UUID categoryId
) {

    public static Product toEntity(ProductRequestDto dto, Category category) {
        Product product = new Product();
        product.setName(dto.name);
        product.setPrice(dto.price);
        product.setAvailable(dto.available);
        product.setCategory(category);

        return product;
    }
}
