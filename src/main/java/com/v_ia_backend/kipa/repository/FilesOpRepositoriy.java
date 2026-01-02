package com.v_ia_backend.kipa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.FilesOp;

@Repository
public interface FilesOpRepositoriy extends JpaRepository<FilesOp, Long> {
    List<FilesOp> findByPaymentsAccountsRelationId_Id(Long paymentsAccountsRelationId);
}
