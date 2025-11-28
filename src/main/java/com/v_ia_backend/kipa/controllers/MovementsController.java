package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.dto.request.LoginRequest;
import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
// import com.v_ia_backend.kipa.dto.request.MovementsRequest; 
import com.v_ia_backend.kipa.service.MovementServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movement")
public class MovementsController {

    @Autowired
    private MovementServiceImpl movementService;

    public MovementsController(MovementServiceImpl movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMovementController(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
    }

    @PostMapping("/get-filter")
    public ResponseEntity<Object> getAllMovementsByFilterController(@Valid @RequestBody MovementFilterRequest movementFilterRequest) {
        return ResponseEntity.ok(movementService.getAllMovementsByFilter(movementFilterRequest));
    }
}
