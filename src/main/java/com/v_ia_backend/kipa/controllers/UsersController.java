package com.v_ia_backend.kipa.controllers;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.dto.request.UsersRequest;
import com.v_ia_backend.kipa.entity.Roles;
import com.v_ia_backend.kipa.entity.Users;
import com.v_ia_backend.kipa.service.RoleServiceImpl;
import com.v_ia_backend.kipa.service.UserServiceImpl;
import com.v_ia_backend.kipa.service.interfaces.StatusUserService;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getUserController(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/getall")
    public ResponseEntity<Object> getAllUsersController() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUserController(@RequestBody UsersRequest request) {
        Users user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUserController(@PathVariable Integer id, @RequestBody UsersRequest request) {
        Users updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }


    @GetMapping("/getall/roles")
    public ResponseEntity<Object> getAllRolesController() {
        List<Roles> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
