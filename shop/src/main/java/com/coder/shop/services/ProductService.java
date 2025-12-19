package com.coder.shop.services;

import java.util.List;

import com.coder.shop.models.Product;

public interface ProductService {
    void add(Product product);
    List<Product> all();
    List<Product> byCatId(int id);
    List<Product> bySubcatid(int id);
    List<Product> byChildcatid(int id);
    List<Product> byTagid(int id);
    Product get(Long id);
    void update(Long id, Product product);
    void drop(Long id);
}
