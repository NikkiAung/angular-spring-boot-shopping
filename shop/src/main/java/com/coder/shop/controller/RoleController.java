package com.coder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @GetMapping()
    public ResponseEntity<List<Role>> allRoles() {
        List<Role> roles = roleService.all();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<Msg> createRole(@RequestParam String name) {
        Role role = new Role();
        role.setName(name);
        roleService.add(role);
        return ResponseEntity.ok(new Msg("Role Create Successfully", HttpStatus.OK.value()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Msg> updateRole(@PathVariable int id, @RequestParam String name) {
        Role role = new Role();
        role.setName(name);
        roleService.update(id, role);
        return ResponseEntity.ok(new Msg("Role Update Successfully", HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> deleteRole(@PathVariable int id) {
        roleService.drop(id);
        return ResponseEntity.ok(new Msg("Role Delete Successfully", HttpStatus.OK.value()));     
    }

}
