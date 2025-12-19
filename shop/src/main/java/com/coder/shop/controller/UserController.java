package com.coder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PatchMapping("add/role/{userId}/{roleId}")
    public ResponseEntity<Msg> addRole(Long userId, int roleId) {
        userService.addRole(userId, roleId);
        return ResponseEntity.ok(new Msg("Role Add Successfully", HttpStatus.OK.value()));
    }


    @PatchMapping("remove/role/{userId}/{roleId}")
    public ResponseEntity<Msg> removeRole(Long userId, int roleId) {
        userService.removeRole(userId, roleId);
        return ResponseEntity.ok(new Msg("Role Remove Successfully", HttpStatus.OK.value()));
    }
}
