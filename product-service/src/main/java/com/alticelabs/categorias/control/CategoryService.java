package com.alticelabs.categorias.control;


import com.alticelabs.categorias.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository repository;

    public List<Category> getAll() {
        return repository.listAll();
    }

    public Category getById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Category create(Category category) {
        repository.persist(category);
        return category;
    }

    @Transactional
    public Category update(UUID id, Category updated) {
        Category category = repository.findById(id);

        if (category == null) {
            return null;
        }

        category.setName(updated.getName());
        category.setDescription(updated.getDescription());

        return category;
    }

    @Transactional
    public boolean delete(UUID id) {
        return repository.deleteById(id);
    }
}
