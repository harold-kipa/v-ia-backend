package com.v_ia_backend.kipa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.AniSubaccounts;

@Repository
public interface AniSubaccountsRepositoriy extends JpaRepository<AniSubaccounts, Long> {
    List<AniSubaccounts> findByType(String type);
}
