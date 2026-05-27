package com.v_ia_backend.kipa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.Occired;
import com.v_ia_backend.kipa.interfase.OcciredFileInterfase;

@Repository
public interface OcciredRepositoriy extends JpaRepository<Occired, Long> {
    List<Occired> findByMovementId_Id(Long movementId);
    List<OcciredFileInterfase> findFileByMovementId_IdIn(List<Long> id);
}
