package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.interfase.HigherAccountInterfase;
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
        List<HigherAccounts> higherAccounts = HigherAccountsRepositoriy.findAll();
        List<HigherAccounts> finalAccounts = new java.util.ArrayList<>();
        higherAccounts.forEach(
            account -> {
                if (!"NO USAR".equals(account.getHigherAccountDescription())){
                    finalAccounts.add(account);
                }
            }
        );
        return finalAccounts;
    }

    @Override
    public List<HigherAccountInterfase> getAllHigherAccountsCapex(String investmentTarget) {
        List<HigherAccountInterfase> higherAccounts = HigherAccountsRepositoriy.findByInvestmentTarget(investmentTarget);
        return higherAccounts;
    }

    @Override
    public HigherAccounts getHigherAccountById(Long id) {
        return HigherAccountsRepositoriy.findById(id).orElse(null);
    }

    @Override
    public HigherAccounts getHigherAccountByHigherAccountsViewId(Long id) {
        return HigherAccountsRepositoriy.findByHigherAccountsViewId_Id(id).stream().findFirst().orElse(null);
    }

    @Override
    public List<HigherAccounts> getAllHigherAccountByHigherAccountsViewId(Long id) {
        return HigherAccountsRepositoriy.findByHigherAccountsViewId_Id(id);
    }
}
