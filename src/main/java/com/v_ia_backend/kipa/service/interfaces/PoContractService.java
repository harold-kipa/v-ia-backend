package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.PoContract;
import com.v_ia_backend.kipa.interfase.PoContractFileInterfase;
import com.v_ia_backend.kipa.interfase.PoContractFilterInterfase;
import com.v_ia_backend.kipa.interfase.PoContractInterfase;

import java.util.List;

@Service
public interface PoContractService {
    List<PoContractFileInterfase> getPoContractFileByMovementIdIn(List<Long> id);
    PoContract getPoContractById(Long id);
    List<PoContractInterfase> getAllPoContract();
    List<PoContract> getPoContractByMovementId(Long id);
    List<PoContractFilterInterfase> getPoContractByConsecutiveAndYear(Long id);
}
