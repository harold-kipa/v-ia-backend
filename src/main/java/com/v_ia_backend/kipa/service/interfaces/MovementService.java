package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.dto.response.CapexResponse;
import com.v_ia_backend.kipa.dto.response.MovementListResponse;
import com.v_ia_backend.kipa.dto.response.MovementResponse;
import com.v_ia_backend.kipa.dto.response.MovementTableResponse;
import com.v_ia_backend.kipa.dto.response.MovementTotalsResponse;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.interfase.MovementsInterfase;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface MovementService {
    MovementResponse getMovementById(Long id);
    MovementTotalsResponse getAllMovementsByFilter(MovementFilterRequest movementFilterRequest);
    List<MovementListResponse> sortMovements(List<MovementsInterfase> movements);
    MovementTotalsResponse calculationsMovements(List<MovementListResponse> movementListResponse, List<Long> cuentasUnicas);
    MovementTotalsResponse calculationsBeforeMovements(List<MovementListResponse> movementListResponse, List<Long> cuentasUnicas, List<MovementTableResponse> movementListResponseBefore);
    List<CapexResponse> getMovementByCapex(Long year);
    List<CapexResponse> getMovementByOpex(Long year);
    BigDecimal stringToLong(String raw);
}
