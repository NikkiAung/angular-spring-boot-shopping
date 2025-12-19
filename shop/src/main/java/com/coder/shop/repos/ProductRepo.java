package com.coder.shop.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.shop.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
    public List<Product> findByCategoryId(int id);
    public List<Product> findBySubcatId(int id);
    public List<Product> findByChildcatId(int id);
    public List<Product> findByTagId(int id);

}
