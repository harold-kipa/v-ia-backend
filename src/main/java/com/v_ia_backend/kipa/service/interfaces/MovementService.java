package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.dto.response.MovementListResponse;
import com.v_ia_backend.kipa.dto.response.MovementResponse;
import com.v_ia_backend.kipa.dto.response.MovementTableResponse;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.interfase.MovementsInterfase;

import java.util.List;

@Service
public interface MovementService {
    MovementResponse getMovementById(Long id);
    List<MovementTableResponse> getAllMovementsByFilter(MovementFilterRequest movementFilterRequest);
    List<MovementListResponse> sortMovements(List<MovementsInterfase> movements);
    List<MovementTableResponse> calculationsMovements(List<MovementListResponse> movementListResponse, List<Long> cuentasUnicas);
    List<MovementTableResponse> calculationsBeforeMovements(List<MovementListResponse> movementListResponse, List<Long> cuentasUnicas, List<MovementTableResponse> movementListResponseBefore);
}
