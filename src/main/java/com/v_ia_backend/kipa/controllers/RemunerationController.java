package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.RemunerationServiceImpl;

@RestController
@RequestMapping("/remuneration")
public class RemunerationController {

    @Autowired
    private RemunerationServiceImpl remunerationService;

    public RemunerationController(RemunerationServiceImpl remunerationService) {
        this.remunerationService = remunerationService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getRemunerationController(@PathVariable Long id) {
        return ResponseEntity.ok(remunerationService.getRemunerationById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllRemunerationController() {
        return ResponseEntity.ok(remunerationService.getAllRemuneration());
    }
}
