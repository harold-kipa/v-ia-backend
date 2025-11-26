package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.repository.MovementsRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.MovementService;

import java.util.List;


@Service
public class MovementServiceImpl implements MovementService {
    private final MovementsRepositoriy MovementsRepositoriy;
    public MovementServiceImpl(MovementsRepositoriy MovementsRepositoriy) {
        this.MovementsRepositoriy = MovementsRepositoriy;
    }

    @Override
    public List<Movements> getAllMovements() {
        return MovementsRepositoriy.findAll();
    }

    @Override
    public Movements getMovementById(Long id) {
        return MovementsRepositoriy.findById(id).orElse(null);
    }
}
