package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.OcciredServiceImpl;

@RestController
@RequestMapping("/occired")
public class OcciredController {

    @Autowired
    private OcciredServiceImpl higherAccountViewService;

    public OcciredController(OcciredServiceImpl higherAccountViewService) {
        this.higherAccountViewService = higherAccountViewService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOcciredController(@PathVariable Long id) {
        return ResponseEntity.ok(higherAccountViewService.getOcciredById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllOcciredController() {
        return ResponseEntity.ok(higherAccountViewService.getAllOccired());
    }

    @GetMapping("/get/movement/{id}")
    public ResponseEntity<Object> getOcciredByMovementIdController(@PathVariable Long id) {
        return ResponseEntity.ok(higherAccountViewService.getOcciredByMovementId(id));
    }
}
