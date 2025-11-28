package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.CostCenterServiceImpl;

@RestController
@RequestMapping("/cost-center")
public class CostCentersController {

    @Autowired
    private CostCenterServiceImpl costCenterService;

    public CostCentersController(CostCenterServiceImpl costCenterService) {
        this.costCenterService = costCenterService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getCostCenterController(@PathVariable Long id) {
        return ResponseEntity.ok(costCenterService.getCostCenterById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllCostCentersController() {
        return ResponseEntity.ok(costCenterService.getAllCostCenters());
    }
}
