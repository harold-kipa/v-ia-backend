package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.HigherAccountsView;

import java.util.List;

@Service
public interface HigherAccountViewService {
    HigherAccountsView getHigherAccountById(Long id);
    public List<HigherAccountsView> getAllHigherAccountView();
}
