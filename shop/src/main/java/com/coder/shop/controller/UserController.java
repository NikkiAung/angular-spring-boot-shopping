package com.coder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.shop.dtos.Msg;
import com.coder.shop.models.AppUser;
import com.coder.shop.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    
    @GetMapping
    public ResponseEntity<List<AppUser>> all() {
        List<AppUser> users = userService.all();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("{id}")
    public ResponseEntity<AppUser> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("add/role/{userId}/{roleId}")
    public ResponseEntity<Msg> addRole(@PathVariable Long userId, @PathVariable int roleId) {
        userService.addRole(userId, roleId);
        return ResponseEntity.ok(new Msg("Role Add Successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("remove/role/{userId}/{roleId}")
    public ResponseEntity<Msg> removeRole(@PathVariable Long userId, @PathVariable int roleId) {
        userService.removeRole(userId, roleId);
        return ResponseEntity.ok(new Msg("Role Remove Successfully", HttpStatus.OK.value()));
    }
}
