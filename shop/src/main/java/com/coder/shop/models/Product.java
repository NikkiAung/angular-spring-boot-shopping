package com.coder.shop.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private List<String> images;

    @ManyToOne
    Category category;
    @ManyToOne
    Subcat subcat;
    @ManyToOne
    Childcat childcat;
    @ManyToOne
    Tag tag;
}
