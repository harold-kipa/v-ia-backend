package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.HigherAccountViewServiceImpl;

@RestController
@RequestMapping("/higher-account")
public class HigherAccountsController {

    @Autowired
    private HigherAccountViewServiceImpl higherAccountViewService;

    public HigherAccountsController(HigherAccountViewServiceImpl higherAccountViewService) {
        this.higherAccountViewService = higherAccountViewService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getHigherAccountController(@PathVariable Long id) {
        return ResponseEntity.ok(higherAccountViewService.getHigherAccountById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllHigherAccountsController() {
        return ResponseEntity.ok(higherAccountViewService.getAllHigherAccountView());
    }
}
