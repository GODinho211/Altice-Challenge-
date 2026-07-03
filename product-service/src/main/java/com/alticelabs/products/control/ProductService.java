package com.alticelabs.products.control;

import com.alticelabs.categorias.control.CategoryRepository;
import com.alticelabs.categorias.entity.Category;
import com.alticelabs.products.boundary.ProductRequestDto;
import com.alticelabs.products.boundary.ProductResponseDto;
import com.alticelabs.products.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    @Inject
    CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return repository.listAll();
    }

    public Optional<Product> getById(UUID id) {
        return repository.findByIdOptional(id);
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto dto) {
        Category category = categoryRepository.findById(dto.categoryId());

        if (category == null) {
            throw new NotFoundException("Category not found");
        }

        Product product = ProductRequestDto.toEntity(dto,category);

        product.persist();

        return ProductResponseDto.from(product);
    }

    @Transactional
    public Product update(UUID id, Product updated) {
        Product product = repository.findById(id);

        if (product == null) {
            return null;
        }

        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setAvailable(updated.isAvailable());
//        product.setCategory(updated.getCategory());

        return product;
    }

    @Transactional
    public boolean delete(UUID id) {
        return repository.deleteById(id);
    }
}