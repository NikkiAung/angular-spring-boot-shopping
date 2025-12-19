package com.coder.shop.impls;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.shop.exceptions.CategoryNotFoundException;
import com.coder.shop.models.Category;
import com.coder.shop.repos.CategoryRepo;
import com.coder.shop.services.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryImpl implements CategoryService{

    @Autowired
    private final CategoryRepo categoryRepo;

    @Override
    public void add(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public List<Category> all() {
        return categoryRepo.findAll();
    }

    @Override
    public Category get(int id) {
        return categoryRepo.findById(id).orElseThrow(()-> new CategoryNotFoundException("No Category With that id"));
    }

    @Override
    public void drop(int id) {
        Category cat = get(id);
        if (cat != null) {
            categoryRepo.deleteById(id);
        }
    }

    @Override
    public void patch(int id, Category category) {
        Category dbCat = get(id);
        if (dbCat != null) {
            if (category.getName() != null) {
                dbCat.setName(category.getName());
            }
            if (category.getImage() != null) {
                dbCat.setImage(category.getImage());
            }
        }
        categoryRepo.save(dbCat);
    }

    @Override
    public void changeName(int id, String name) {
        Category cat = get(id);
        if (cat != null) {
            cat.setName(name);
            categoryRepo.save(cat);
        }
    }

    @Override
    public void changeImage(int id, String image) {
        Category cat = get(id);
        if (cat != null) {
            cat.setImage(image);
            categoryRepo.save(cat);
        }
    }
    
}
