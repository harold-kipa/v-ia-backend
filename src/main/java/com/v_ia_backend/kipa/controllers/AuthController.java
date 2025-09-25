package com.v_ia_backend.kipa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("/login")
    public String login() {
        return "Login successful";
    }
    @PostMapping("/login")
    public String register() {
        return "register successful";
    }
    
}
