package com.v_ia_backend.kipa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.PoContract;
import com.v_ia_backend.kipa.interfase.PoContractFileInterfase;
import com.v_ia_backend.kipa.interfase.PoContractFilterInterfase;
import com.v_ia_backend.kipa.interfase.PoContractInterfase;

@Repository
public interface PoContractRepositoriy extends JpaRepository<PoContract, Long> {
    
    List<PoContractInterfase> findAllProjectedBy();
    List<PoContract> findByMovementId_Id(Long movementId);
    List<PoContractFilterInterfase> findByConsecutiveAndYear(String Consecutive, Long Year);
    List<PoContractFileInterfase> findFileByMovementId_IdIn(List<Long> id);
    // List<PoContractInterfase> findByMovementId(Long movementId);
    // <T> List<T> findAll(Class<T> type);
}
