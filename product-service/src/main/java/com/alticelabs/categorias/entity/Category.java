package com.alticelabs.categorias.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Category extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 25, nullable = false ,  unique = true)
    private String name;

    @Column(length = 250, nullable = false ,  unique = true)
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(){

    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
