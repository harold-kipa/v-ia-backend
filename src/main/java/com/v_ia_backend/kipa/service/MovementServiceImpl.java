package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.dto.response.MovementListResponse;
import com.v_ia_backend.kipa.dto.response.MovementTableResponse;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.repository.MovementsRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.MovementService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MovementServiceImpl implements MovementService {
    private final MovementsRepositoriy MovementsRepositoriy;
    public record MovementGroupKey(Long higherAccountId, String movementDescription) {}
    public MovementServiceImpl(MovementsRepositoriy MovementsRepositoriy) {
        this.MovementsRepositoriy = MovementsRepositoriy;
    }

    @Override
    public List<MovementListResponse> getAllMovementsByFilter(MovementFilterRequest movementFilterRequest) {
        List<Movements> movements = this.MovementsRepositoriy.findByMovementDateBetweenAndHigherAccountId_IdBetweenAndAuxiliaryId_Id(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId(), movementFilterRequest.getAuxiliaryId());
        movements.sort(
            Comparator.comparing(Movements::getMovementDescription)
            .thenComparing(
                (Movements m) -> m.getHigherAccountId(),
                Comparator.nullsLast(
                Comparator.comparing(HigherAccounts::getId)
                )
            )
        );
        List<Long> cuentasUnicas = movements.stream()
                .map(m -> m.getHigherAccountId().getId())   // <- Obtienes el Long
                .distinct()
                .collect(Collectors.toList());

        System.out.println(cuentasUnicas);
        List<MovementListResponse> responses = new java.util.ArrayList<>();


        List<MovementListResponse> movementListResponse = new java.util.ArrayList<>();
        movements.forEach(movement -> {
            long[] beforeCredit = {0L};
            long[] beforeDebit = {0L};
            MovementListResponse movementListResponse1 = new MovementListResponse();
            List<MovementTableResponse> tableResponse = new java.util.ArrayList<>();
            cuentasUnicas.forEach(cuentaUnica -> {
                MovementTableResponse tableResponse1 = new MovementTableResponse();
                if(movement.getHigherAccountId().getId().equals(cuentaUnica)){
                    tableResponse1.setHigherAccountId(movement.getHigherAccountId());
                    tableResponse1.setAuxiliaryId(movement.getAuxiliaryId());
                    tableResponse1.setMovementDate(movement.getMovementDate());
                    tableResponse.add(tableResponse1);
                    if (movement.getNatureId().getId() == 1L){
                        beforeDebit[0] += Long.parseLong(movement.getVoucherAmount());
                    } else{
                        beforeCredit[0] += Long.parseLong(movement.getVoucherAmount());
                    }
                    // beforeCredit[0] += movement.getVoucherNumber();
                }

            });
            movementListResponse1.setMovementsList(tableResponse);
            movementListResponse1.setCredit(beforeCredit[0]);
            movementListResponse1.setDebit(beforeDebit[0]);
            movementListResponse.add(movementListResponse1);
            // movementListResponse1.setBalance(BigDecimal.ZERO.longValue());
        });
        
        // responses.add();

        // Movements beforeMovement = new Movements();
        // movements.forEach(movement -> {
        //     if(beforeMovement != null && movement.getHigherAccountId() != null && beforeMovement.getHigherAccountId() != null &&
        //        movement.getHigherAccountId().getId().equals(beforeMovement.getHigherAccountId().getId())) {
        //         // Mismo higherAccountId que el anterior

        //         System.out.println("  -> Mismo HigherAccount ID que el anterior: " + movement.getHigherAccountId().getId());
        //     } else {
        //         // Diferente higherAccountId
        //         System.out.println("-> Nuevo HigherAccount ID encontrado: " + (movement.getHigherAccountId() != null ? movement.getHigherAccountId().getId() : "null"));
        //     }
            
        //     System.out.println("Movimiento ID: " + movement.getId() + ", HigherAccount ID: " + (movement.getHigherAccountId() != null ? movement.getHigherAccountId().getId() : "null"));
        // });
        // // 2. Agrupar SOLO por higherAccountId (id)
        // Map<Long, List<Movements>> gruposPorCuentaMayor = movements.stream()
        //     .collect(Collectors.groupingBy(
        //         m -> m.getHigherAccountId() != null ? m.getHigherAccountId().getId() : null
        //     ));

        // // 3. Por cada grupo, construir un MovementListResponse
        // List<MovementListResponse> responses = gruposPorCuentaMayor.entrySet().stream()
        //     .map(entry -> {
        //         Long higherAccountId = entry.getKey();
        //         List<Movements> lista = entry.getValue();

        //         // Convertir cada Movements a MovementTableResponse
        //         List<MovementTableResponse> tableResponses = lista.stream()
        //             .map(MovementTableResponse::new)
        //             .toList();

        //         // Aquí puedes meter tu lógica real de débito / crédito.
        //         Long debit = 0L;
        //         Long credit = 0L;
        //         Long balance = debit - credit;

        //         MovementListResponse response = new MovementListResponse();
        //         response.setMovementsList(tableResponses);
        //         response.setDebit(debit);
        //         response.setCredit(credit);
        //         response.setBalance(balance);

        //         return response;
        //     })
        //     .toList();

        return movementListResponse;
    }

    @Override
    public Movements getMovementById(Long id) {
        return MovementsRepositoriy.findById(id).orElse(null);
    }
}
