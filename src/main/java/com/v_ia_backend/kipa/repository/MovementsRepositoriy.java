package com.v_ia_backend.kipa.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.Movements;

@Repository
public interface MovementsRepositoriy extends JpaRepository<Movements, Long> {
    List<Movements> findByMovementDateBetweenAndHigherAccountId_IdBetweenAndAuxiliaryId_Id(Timestamp fechaInicio, Timestamp fechaFin, Long initialAccount, Long finalAccount, Long auxiliaryId);
    List<Movements> findByMovementDateBetweenAndHigherAccountId_IdBetween(Timestamp fechaInicio, Timestamp fechaFin, Long initialAccount, Long finalAccount);
    List<Movements> findByAuxiliaryId_Id(Long auxiliaryId);
    List<Movements> findByMovementDateBetween(Timestamp fechaInicio, Timestamp fechaFin);
    List<Movements> findByPoContractId_Id(Long poContractId);
    List<Movements> findByPaymentsAccountsRelationId_Id(Long paymentsAccountsRelationId);


}
