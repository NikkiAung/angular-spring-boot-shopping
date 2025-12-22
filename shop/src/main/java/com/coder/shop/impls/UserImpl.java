package com.coder.shop.impls;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.shop.exceptions.UserNotFoundException;
import com.coder.shop.models.AppUser;
import com.coder.shop.models.Role;
import com.coder.shop.repos.RoleRepo;
import com.coder.shop.repos.UserRepo;
import com.coder.shop.services.UserService;
import com.coder.shop.exceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final RoleRepo roleRepo;

    @Override
    public void register(AppUser user) {
        userRepo.save(user);
    }

    @Override
    public AppUser get(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("No user with that id"));
    }

    private Role getRole(int roleId) {
        return roleRepo.findById(roleId).orElseThrow(()-> new RoleNotFoundException("No role with that id"));
    }

    @Override
    public void addRole(Long userId, int roleId) {
        AppUser user = get(userId);
        Role role = getRole(roleId);
        if(user != null && role != null) {
            Set<Role> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
            userRepo.save(user);
        }
        
    }

    @Override
    public List<AppUser> all() {
        return userRepo.findAll();
    }

    @Override
    public void removeRole(Long userId, int roleId) {
        AppUser user = get(userId);
        Role role = getRole(roleId);
        if(user != null && role != null) {
            Set<Role> roles = user.getRoles();
            roles.remove(role);
            user.setRoles(roles);
            userRepo.save(user);
        }
    }

    @Override
    public Set<Role> getUserRoles(Long userId) {
        AppUser user = get(userId);
        return user.getRoles();
    }

    @Override
    public AppUser findByName(String name) {
        return userRepo.findByName(name);
    }

}
