package com.v_ia_backend.kipa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.FilesOccired;

@Repository
public interface FilesOcciredRepositoriy extends JpaRepository<FilesOccired, Long> {
    // List<FilesOccired> findByPaymentsAccountsRelationId_Id(Long paymentsAccountsRelationId);
}
