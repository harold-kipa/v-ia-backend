package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.PoContract;

import java.util.List;

@Service
public interface PoContractService {
    PoContract getPoContractById(Long id);
    List<PoContract> getAllPoContract();
}
