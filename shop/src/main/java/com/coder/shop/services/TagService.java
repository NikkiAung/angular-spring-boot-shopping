package com.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coder.shop.models.Tag;

@Service
public interface TagService {
    void add(Tag tag);
    
    List<Tag> all();

    Tag get(int id);

    void update(int id, Tag tag);

    void nameChange(int id, String name);

    void imageChange(int id, String image);

    void drop(int id);
}
