package com.coder.shop.services;

import java.util.List;
import com.coder.shop.models.Category;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    void add (Category category);

    List<Category> all();

    Category get(int id);

    void drop(int id);

    void patch(int id, Category category);

    void changeName(int id, String name);

    void changeImage (int id, String image);
}
