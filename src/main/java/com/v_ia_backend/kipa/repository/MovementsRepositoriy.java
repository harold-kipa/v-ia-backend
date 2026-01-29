package com.v_ia_backend.kipa.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.interfase.MovementsInterfase;
import com.v_ia_backend.kipa.interfase.MovementsYearInterfase;

@Repository
public interface MovementsRepositoriy extends JpaRepository<Movements, Long> {
    List<MovementsInterfase> findByMovementDateBetweenAndHigherAccountId_IdBetweenAndAuxiliaryId_Id(Timestamp fechaInicio, Timestamp fechaFin, Long initialAccount, Long finalAccount, Long auxiliaryId);
    List<MovementsInterfase> findByMovementDateBetweenAndHigherAccountId_IdBetween(Timestamp fechaInicio, Timestamp fechaFin, Long initialAccount, Long finalAccount);
    List<MovementsInterfase> findByMovementDateBetweenAndAuxiliaryId_Id(Timestamp fechaInicio, Timestamp fechaFin, Long auxiliaryId);
    List<MovementsInterfase> findByHigherAccountId_IdBetween(Long initialAccount, Long finalAccount);
    List<MovementsInterfase> findByAuxiliaryId_Id(Long auxiliaryId);
    List<MovementsInterfase> findByMovementDateBetween(Timestamp fechaInicio, Timestamp fechaFin);
    List<MovementsInterfase> findByPoContractId_Id(Long poContractId);
    List<MovementsInterfase> findByMovementDateBetweenAndHigherAccountId_IdBetweenAndPaymentsAccountsRelationId_Id(Timestamp fechaInicio, Timestamp fechaFin, Long initialAccount, Long finalAccount, Long poContractId);
    List<MovementsInterfase> findByPaymentsAccountsRelationId_Id(Long paymentsAccountsRelationId);
    // List<MovementsYearInterfase> findByMovementDateBetweenAndHigherAccountId_IdIn(Timestamp initialAccount, Timestamp finalAccount, Long higherAccountId);
    List<MovementsYearInterfase> findByMovementDateBetweenAndHigherAccountId_IdIn(
            Timestamp initialDate,
            Timestamp finalDate,
            List<Long> higherAccountIds
    );



}
