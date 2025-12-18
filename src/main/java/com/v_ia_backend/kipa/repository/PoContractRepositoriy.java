package com.v_ia_backend.kipa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.PoContract;
import com.v_ia_backend.kipa.interfase.PoContractInterfase;

@Repository
public interface PoContractRepositoriy extends JpaRepository<PoContract, Long> {
    List<PoContractInterfase> findAllProjectedBy();
    // List<PoContractInterfase> findByMovementId(Long movementId);
    // <T> List<T> findAll(Class<T> type);
}
