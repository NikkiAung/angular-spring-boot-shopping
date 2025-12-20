package com.coder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.shop.dtos.Msg;
import com.coder.shop.models.Role;
import com.coder.shop.services.RoleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/roles")
@AllArgsConstructor
public class RoleController {
    @Autowired
    private final RoleService roleService;


    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping()
    public ResponseEntity<List<Role>> all() {
        List<Role> roles = roleService.all();
        return ResponseEntity.ok(roles);
    }


    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("{id}")
    public ResponseEntity<Role> get(@PathVariable int id) {
        return ResponseEntity.ok(roleService.get(id));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> createRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleService.add(role);
        return ResponseEntity.ok(new Msg("Role Create Successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("/{id}")
    public ResponseEntity<Msg> updateRole(@PathVariable int id, String name) {
        Role role = new Role();
        role.setName(name);
        roleService.update(id, role);
        return ResponseEntity.ok(new Msg("Role Update Successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> deleteRole(@PathVariable int id) {
        roleService.drop(id);
        return ResponseEntity.ok(new Msg("Role Delete Successfully", HttpStatus.OK.value()));     
    }

}
