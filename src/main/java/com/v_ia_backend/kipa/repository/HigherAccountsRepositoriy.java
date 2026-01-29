package com.v_ia_backend.kipa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.interfase.HigherAccountInterfase;

@Repository
public interface HigherAccountsRepositoriy extends JpaRepository<HigherAccounts, Long> {
    List<HigherAccounts> findByHigherAccountsViewId_Id(Long higherAccountsViewId);
    List<HigherAccountInterfase> findByInvestmentTarget(String investmentTarget);
}
