package com.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.shop.models.Product;
import com.coder.shop.repos.ProductRepo;
import com.coder.shop.services.ProductService;
import com.coder.shop.exceptions.ProductNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductImpl implements ProductService{
    @Autowired
    private final ProductRepo productRepo;

    @Override
    public void add(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public List<Product> all() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> byCatId(int id) {
        return productRepo.findByCategoryId(id);
    }

    @Override
    public List<Product> bySubcatid(int id) {
        return productRepo.findBySubcatId(id);
    }

    @Override
    public List<Product> byChildcatid(int id) {
        return productRepo.findByChildcatId(id);
    }

    @Override
    public List<Product> byTagid(int id) {
        return productRepo.findByTagId(id);
    }

    @Override
    public Product get(Long id) {
        return productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("No product with that id"));
    }

    @Override
    public void update(Long id, Product product) {
        Product dbProduct = get(id);
        if (dbProduct != null) {
            dbProduct.setName(product.getName());
            dbProduct.setPrice(product.getPrice());
            dbProduct.setImages(product.getImages());
            dbProduct.setCategory(product.getCategory());
            dbProduct.setSubcat(product.getSubcat());
            dbProduct.setChildcat(product.getChildcat());
            productRepo.save(dbProduct);
        }
    }

    @Override
    public void drop(Long id) {
        Product dbProduct = get(id);
        if(dbProduct != null) {
            productRepo.deleteById(id);;
        }
    }
}
