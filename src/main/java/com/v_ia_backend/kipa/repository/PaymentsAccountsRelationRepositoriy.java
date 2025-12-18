package com.v_ia_backend.kipa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.PaymentsAccountsRelation;
import com.v_ia_backend.kipa.interfase.PaymentsAccountsRelationInterfase;

@Repository
public interface PaymentsAccountsRelationRepositoriy extends JpaRepository<PaymentsAccountsRelation, Long> {
    List<PaymentsAccountsRelationInterfase> findAllProjectedBy();
    List<PaymentsAccountsRelation> findByConsecutiveNumber(String consecutiveNumber);
    // <T> List<T> findAll(Class<T> type);
}
