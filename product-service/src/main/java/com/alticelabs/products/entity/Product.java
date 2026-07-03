package com.alticelabs.products.entity;


import com.alticelabs.categorias.entity.Category;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product extends PanacheEntityBase {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private BigDecimal price;

    private boolean available;

    @ManyToOne
    private Category category;

    public Product(String name, BigDecimal price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public Product() {
    }

    public UUID getId(){
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public boolean isAvailable() {
        return available;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


}
