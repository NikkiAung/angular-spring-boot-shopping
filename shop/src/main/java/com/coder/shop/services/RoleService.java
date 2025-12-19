package com.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coder.shop.models.Role;

@Service
public interface RoleService {
    void add(Role role);
    List<Role> all();
    Role get(int id);
    void update(int id, Role role);
    void drop(int id);
}
