package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Movements;

import java.util.List;

@Service
public interface MovementService {
    Movements getMovementById(Long id);
    List<Movements> getAllMovements();
}
