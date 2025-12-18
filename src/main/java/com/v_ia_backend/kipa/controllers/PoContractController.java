package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.PoContractServiceImpl;

@RestController
@RequestMapping("/po-contract")
public class PoContractController {

    @Autowired
    private PoContractServiceImpl poContractService;

    public PoContractController(PoContractServiceImpl poContractService) {
        this.poContractService = poContractService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getPoContractController(@PathVariable Long id) {
        return ResponseEntity.ok(poContractService.getPoContractById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllPoContractController() {
        return ResponseEntity.ok(poContractService.getAllPoContract());
    }
}
