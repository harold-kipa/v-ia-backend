package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.ArhClasification;
import com.v_ia_backend.kipa.repository.ArhClasificationRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.ArhClasificationService;

import java.util.List;


@Service
public class ArhClasificationServiceImpl implements ArhClasificationService {
    private final ArhClasificationRepositoriy ArhClasificationRepositoriy;
    public ArhClasificationServiceImpl(ArhClasificationRepositoriy ArhClasificationRepositoriy) {
        this.ArhClasificationRepositoriy = ArhClasificationRepositoriy;
    }

    @Override
    public List<ArhClasification> getAllArhClasification() {
        List<ArhClasification> higherAccounts = ArhClasificationRepositoriy.findAll();
        // List<ArhClasification> finalAccounts = new java.util.ArrayList<>();
        // higherAccounts.forEach(
        //     account -> {
        //         if (!"NO USAR".equals(account.getArhClasificationDescription())){
        //             finalAccounts.add(account);
        //         }
        //     }
        // );
        return higherAccounts;
    }

    @Override
    public ArhClasification getArhClasificationById(Long id) {
        return ArhClasificationRepositoriy.findById(id).orElse(null);
    }
}
