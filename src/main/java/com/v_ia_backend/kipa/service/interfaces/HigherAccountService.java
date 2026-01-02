package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.HigherAccounts;

import java.util.List;

@Service
public interface HigherAccountService {
    HigherAccounts getHigherAccountById(Long id);
    List<HigherAccounts> getAllHigherAccounts();
    public HigherAccounts getHigherAccountByHigherAccountsViewId(Long id);
}
