package com.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coder.shop.models.Childcat;

@Service
public interface ChildcatService {

    void add(Childcat childcat);

    List<Childcat> all();

    Childcat get(int id);

    void update(int id, Childcat childcat);

    void nameChange(int id, String name);

    void imageChange(int id, String image);

    void drop(int id);
}
