package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.repository.HigherAccountsRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.HigherAccountService;

import java.util.List;


@Service
public class HigherAccountServiceImpl implements HigherAccountService {
    private final HigherAccountsRepositoriy HigherAccountsRepositoriy;
    public HigherAccountServiceImpl(HigherAccountsRepositoriy HigherAccountsRepositoriy) {
        this.HigherAccountsRepositoriy = HigherAccountsRepositoriy;
    }

    @Override
    public List<HigherAccounts> getAllHigherAccounts() {
        return HigherAccountsRepositoriy.findAll();
    }

    @Override
    public HigherAccounts getHigherAccountById(Long id) {
        return HigherAccountsRepositoriy.findById(id).orElse(null);
    }
}
