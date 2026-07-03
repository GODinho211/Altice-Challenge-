package com.alticelabs.products.boundary;

import com.alticelabs.products.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name,
        BigDecimal price,
        boolean available,
        String categoryName
) {

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.isAvailable(),
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }
}
