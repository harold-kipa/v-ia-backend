package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Auxiliaries;

import java.util.List;

@Service
public interface AuxiliaryService {
    Auxiliaries getAuxiliaryById(Long id);
    List<Auxiliaries> getAllAuxiliaries();
}
