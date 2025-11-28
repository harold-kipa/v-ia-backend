package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.AuxiliaryServiceImpl;

@RestController
@RequestMapping("/auxiliary")
public class AuxiliariesController {

    @Autowired
    private AuxiliaryServiceImpl auxiliaryService;

    public AuxiliariesController(AuxiliaryServiceImpl auxiliaryService) {
        this.auxiliaryService = auxiliaryService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getAuxiliaryController(@PathVariable Long id) {
        return ResponseEntity.ok(auxiliaryService.getAuxiliaryById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllAuxiliariesController() {
        return ResponseEntity.ok(auxiliaryService.getAllAuxiliaries());
    }
}
