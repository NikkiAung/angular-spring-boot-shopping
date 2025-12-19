package com.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.coder.shop.models.Subcat;

@Service
public interface SubcatService {
    void add(Subcat subcat);
    Subcat get(int id);
    List<Subcat> all();
    void update(int id, Subcat subcat);
    void changeName(int id, String name);
    void imageChange(int id, String image);
    void drop(int id);
}

