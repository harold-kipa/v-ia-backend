package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.PoContract;
import com.v_ia_backend.kipa.repository.PoContractRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.PoContractService;

import java.util.List;


@Service
public class PoContractServiceImpl implements PoContractService {
    private final PoContractRepositoriy poContractRepositoriy;
    public PoContractServiceImpl(PoContractRepositoriy PoContractRepositoriy) {
        this.poContractRepositoriy = PoContractRepositoriy;
    }

    @Override
    public List<PoContract> getAllPoContract() {
        return poContractRepositoriy.findAll();
    }

    @Override
    public PoContract getPoContractById(Long id) {
        return poContractRepositoriy.findById(id).orElse(null);
    }
}
