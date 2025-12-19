package com.coder.shop.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.coder.shop.models.AppUser;
import com.coder.shop.models.Role;

@Service
public interface UserService {
    List<AppUser> all();
    void register(AppUser user);
    void addRole(Long userId, int roleId);
    void removeRole(Long userId, int roleId);
    AppUser get(Long id);
    public Set<Role> getUserRoles(Long userId);
}
