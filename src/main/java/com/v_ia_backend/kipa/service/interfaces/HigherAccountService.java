package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.interfase.HigherAccountInterfase;

import java.util.List;

@Service
public interface HigherAccountService {
    HigherAccounts getHigherAccountById(Long id);
    List<HigherAccounts> getAllHigherAccounts();
    List<HigherAccountInterfase> getAllHigherAccountsCapex(String investmentTarget);
    public HigherAccounts getHigherAccountByHigherAccountsViewId(Long id);
    public List<HigherAccounts> getAllHigherAccountByHigherAccountsViewId(Long id);
}