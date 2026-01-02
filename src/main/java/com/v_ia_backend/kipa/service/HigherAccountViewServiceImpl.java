package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.HigherAccountsView;
import com.v_ia_backend.kipa.repository.HigherAccountsViewRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.HigherAccountViewService;

import java.util.List;


@Service
public class HigherAccountViewServiceImpl implements HigherAccountViewService {
    private final HigherAccountsViewRepositoriy higherAccountViewRepositoriy;
    public HigherAccountViewServiceImpl(HigherAccountsViewRepositoriy higherAccountViewRepositoriy) {
        this.higherAccountViewRepositoriy = higherAccountViewRepositoriy;
    }

    @Override
    public List<HigherAccountsView> getAllHigherAccountView() {
        List<HigherAccountsView> higherAccounts = higherAccountViewRepositoriy.findAll();
        // List<HigherAccountsView> finalAccounts = new java.util.ArrayList<>();
        // higherAccounts.forEach(
        //     account -> {
        //         if (!"NO USAR".equals(account.getHigherAccountDescription())){
        //             finalAccounts.add(account);
        //         }
        //     }
        // );
        return higherAccounts;
    }

    @Override
    public HigherAccountsView getHigherAccountById(Long id) {
        return higherAccountViewRepositoriy.findById(id).orElse(null);
    }
}
