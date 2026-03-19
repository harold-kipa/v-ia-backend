package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.ArhClasification;

import java.util.List;

@Service
public interface ArhClasificationService {
    ArhClasification getArhClasificationById(Long id);
    public List<ArhClasification> getAllArhClasification();
}
