package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.PaymentsAccountsRelation;
import com.v_ia_backend.kipa.interfase.PaymentsAccountsRelationInterfase;

import java.util.List;

@Service
public interface PaymentsAccountsRelationService {
    PaymentsAccountsRelation getPaymentsAccountsRelationById(Long id);
    List<PaymentsAccountsRelationInterfase> getAllPaymentsAccountsRelation();
    List<PaymentsAccountsRelation> getPaymentsAccountsRelationByConsecutiveNumber(String consecutiveNumber);
}
