package com.v_ia_backend.kipa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.dto.request.LoginRequest;
import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.entity.Movements;
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

    @PostMapping("/get/files")
    public ResponseEntity<Object> getAllFilesByMovementsController(@RequestBody List<Long> movementIds) {
        return ResponseEntity.ok(movementService.getAllFilesByMovements(movementIds));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMovementController(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
        // Movements pdfResponse = movementService.getMovementById(id);
        // return ResponseEntity.ok()
        //             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= massiveMachinerySolicitude.pdf")
        //             .contentType(MediaType.APPLICATION_PDF)
        //             .body(pdfResponse.getPoContractId().getFileId().getFileUrl());

    }

    @GetMapping("/get/capex/{id}")
    public ResponseEntity<Object> getMovementCapexController(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementByCapex(id));

    }

    @GetMapping("/get/opex/{id}")
    public ResponseEntity<Object> getMovementOpexController(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementByOpex(id));

    }

    @PostMapping("/get-filter")
    public ResponseEntity<Object> getAllMovementsByFilterController(@Valid @RequestBody MovementFilterRequest movementFilterRequest) {
        return ResponseEntity.ok(movementService.getAllMovementsByFilter(movementFilterRequest));
    }
}
