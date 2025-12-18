package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.PaymentsAccountsRelationServiceImpl;

@RestController
@RequestMapping("/payments-accounts-relation")
public class PaymentsAccountsRelationController {

    @Autowired
    private PaymentsAccountsRelationServiceImpl paymentsAccountsRelationService;

    public PaymentsAccountsRelationController(PaymentsAccountsRelationServiceImpl paymentsAccountsRelationService) {
        this.paymentsAccountsRelationService = paymentsAccountsRelationService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getPaymentsAccountsRelationController(@PathVariable Long id) {
        return ResponseEntity.ok(paymentsAccountsRelationService.getPaymentsAccountsRelationById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllPaymentsAccountsRelationController() {
        return ResponseEntity.ok(paymentsAccountsRelationService.getAllPaymentsAccountsRelation());
    }
}
