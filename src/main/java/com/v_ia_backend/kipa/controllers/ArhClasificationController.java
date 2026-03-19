package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.ArhClasificationServiceImpl;

@RestController
@RequestMapping("/arh-clasification")
public class ArhClasificationController {

    @Autowired
    private ArhClasificationServiceImpl ArhClasificationService;

    public ArhClasificationController(ArhClasificationServiceImpl ArhClasificationService) {
        this.ArhClasificationService = ArhClasificationService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getArhClasificationController(@PathVariable Long id) {
        return ResponseEntity.ok(ArhClasificationService.getArhClasificationById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllArhClasificationController() {
        return ResponseEntity.ok(ArhClasificationService.getAllArhClasification());
    }
}
