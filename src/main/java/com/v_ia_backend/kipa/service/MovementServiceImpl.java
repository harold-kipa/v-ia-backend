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
            java.math.BigDecimal[] beforeCredit = {java.math.BigDecimal.ZERO};
            java.math.BigDecimal[] beforeDebit = {java.math.BigDecimal.ZERO};
            MovementListResponse movementListResponse1 = new MovementListResponse();
            List<MovementTableResponse> tableResponse = new java.util.ArrayList<>();
            cuentasUnicas.forEach(cuentaUnica -> {
                MovementTableResponse tableResponse1 = new MovementTableResponse();
                if(movement.getHigherAccountId().getId().equals(cuentaUnica)){
                    tableResponse1.setHigherAccountId(movement.getHigherAccountId());
                    tableResponse1.setAuxiliaryId(movement.getAuxiliaryId());
                    tableResponse1.setMovementDate(movement.getMovementDate());
                    tableResponse.add(tableResponse1);
                    // Parse voucherAmount which may contain commas and decimals, e.g. "-163,072,118.23"
                    String raw = movement.getVoucherAmount();
                    java.math.BigDecimal amount = java.math.BigDecimal.ZERO;
                    if(raw != null && !raw.isBlank()){
                        // Normalize: remove grouping separators and trim
                        String cleaned = raw.replaceAll("[,\\s]", "");
                        try{
                            amount = new java.math.BigDecimal(cleaned);
                        } catch (NumberFormatException ex){
                            // If parsing fails, fallback to zero to avoid crashing; log the problem
                            System.err.println("Failed parsing voucherAmount='" + raw + "' for movement id=" + movement.getId() + ": " + ex.getMessage());
                            amount = java.math.BigDecimal.ZERO;
                        }
                    }
                    if (movement.getNatureId() != null && movement.getNatureId().getId() == 1L){
                        beforeDebit[0] = beforeDebit[0].add(amount);
                    } else{
                        beforeCredit[0] = beforeCredit[0].add(amount);
                    }
                    // beforeCredit[0] += movement.getVoucherNumber();
                }

            });
            movementListResponse1.setMovementsList(tableResponse);
            // MovementListResponse expects Longs; convert by rounding to nearest whole unit
            movementListResponse1.setCredit(beforeCredit[0].setScale(0, java.math.RoundingMode.HALF_UP).longValue());
            movementListResponse1.setDebit(beforeDebit[0].setScale(0, java.math.RoundingMode.HALF_UP).longValue());
            movementListResponse1.setBalance(beforeDebit[0].subtract(beforeCredit[0]).setScale(0, java.math.RoundingMode.HALF_UP).longValue());
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
