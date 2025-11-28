package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.repository.MovementsRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.MovementService;

import java.util.Comparator;
import java.util.List;


@Service
public class MovementServiceImpl implements MovementService {
    private final MovementsRepositoriy MovementsRepositoriy;
    public MovementServiceImpl(MovementsRepositoriy MovementsRepositoriy) {
        this.MovementsRepositoriy = MovementsRepositoriy;
    }

    @Override
    public List<Movements> getAllMovementsByFilter(MovementFilterRequest movementFilterRequest) {
        List<Movements> movements = this.MovementsRepositoriy.findByMovementDateBetweenAndHigherAccountId_IdBetween(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId());
        movements.sort(
            Comparator.comparing(
                (Movements m) -> m.getHigherAccountId(),
                Comparator.nullsLast(
                    Comparator.comparing(HigherAccounts::getId)
                )
            )
        );
        
        return movements;
    }

    @Override
    public Movements getMovementById(Long id) {
        return MovementsRepositoriy.findById(id).orElse(null);
    }
}
