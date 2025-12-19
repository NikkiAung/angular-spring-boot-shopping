package com.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.shop.models.Role;
import com.coder.shop.repos.RoleRepo;
import com.coder.shop.services.RoleService;
import com.coder.shop.exceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleImpl implements RoleService{

    @Autowired
    private final RoleRepo roleRepo;

    @Override
    public void add(Role role) {
        roleRepo.save(role);
    }

    @Override
    public List<Role> all() {
        return roleRepo.findAll();
    }

    @Override
    public Role get(int id) {
        return roleRepo.findById(id).orElseThrow(() -> new RoleNotFoundException("No role with that id"));
    }

    @Override
    public void update(int id, Role role) {
        Role dbRole = get(id);
        if (dbRole != null) {
            dbRole.setName(role.getName());
            roleRepo.save(role);
        }
    }

    @Override
    public void drop(int id) {
        Role dbRole = get(id);
        if (dbRole != null) {
            roleRepo.deleteById(id);
        }
    }

}
