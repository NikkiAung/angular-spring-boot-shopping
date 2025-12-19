package com.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.shop.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}