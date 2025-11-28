package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.repository.AuxiliariesRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.AuxiliaryService;

import java.util.List;


@Service
public class AuxiliaryServiceImpl implements AuxiliaryService {
    private final AuxiliariesRepositoriy auxiliariesRepositoriy;
    public AuxiliaryServiceImpl(AuxiliariesRepositoriy AuxiliariesRepositoriy) {
        this.auxiliariesRepositoriy = AuxiliariesRepositoriy;
    }

    @Override
    public List<Auxiliaries> getAllAuxiliaries() {
        return auxiliariesRepositoriy.findAll();
    }

    @Override
    public Auxiliaries getAuxiliaryById(Long id) {
        return auxiliariesRepositoriy.findById(id).orElse(null);
    }
}
