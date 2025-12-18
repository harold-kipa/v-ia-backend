package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.PaymentsAccountsRelation;
import com.v_ia_backend.kipa.repository.PaymentsAccountsRelationRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.PaymentsAccountsRelationService;
import com.v_ia_backend.kipa.interfase.PaymentsAccountsRelationInterfase;

import java.util.List;


@Service
public class PaymentsAccountsRelationServiceImpl implements PaymentsAccountsRelationService {
    private final PaymentsAccountsRelationRepositoriy paymentsAccountsRelationRepositoriy;
    public PaymentsAccountsRelationServiceImpl(PaymentsAccountsRelationRepositoriy PaymentsAccountsRelationRepositoriy) {
        this.paymentsAccountsRelationRepositoriy = PaymentsAccountsRelationRepositoriy;
    }

    @Override
    public List<PaymentsAccountsRelationInterfase> getAllPaymentsAccountsRelation() {
        List<PaymentsAccountsRelationInterfase> paymentsAccountsRelationList = paymentsAccountsRelationRepositoriy.findAllProjectedBy();
        return paymentsAccountsRelationList;
    }

    @Override
    public PaymentsAccountsRelation getPaymentsAccountsRelationById(Long id) {
        return paymentsAccountsRelationRepositoriy.findById(id).orElse(null);
    }
    @Override
    public List<PaymentsAccountsRelation> getPaymentsAccountsRelationByConsecutiveNumber(String consecutiveNumber) {
        return paymentsAccountsRelationRepositoriy.findByConsecutiveNumber(consecutiveNumber);
    }
}
