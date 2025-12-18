package com.v_ia_backend.kipa.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.dto.response.MovementListResponse;
import com.v_ia_backend.kipa.dto.response.MovementTableResponse;
import com.v_ia_backend.kipa.entity.Files;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.exception.listexceptions.ConflictException;
import com.v_ia_backend.kipa.repository.MovementsRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.MovementService;
import java.nio.file.Path;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Base64;
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
        List<Movements> movements;
        if(movementFilterRequest.getAuxiliaryId() == null && movementFilterRequest.getInitialAccountId() == null && movementFilterRequest.getFinalAccountId() == null){
            movements = this.MovementsRepositoriy.findByMovementDateBetween(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate());
        }
        else if(movementFilterRequest.getAuxiliaryId() == null){
            movements = this.MovementsRepositoriy.findByMovementDateBetweenAndHigherAccountId_IdBetween(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId());
        }
        else{
            movements = this.MovementsRepositoriy.findByMovementDateBetweenAndHigherAccountId_IdBetweenAndAuxiliaryId_Id(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId(), movementFilterRequest.getAuxiliaryId());
        }
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
            MovementListResponse movementListResponse1 = movementListResponse.stream().filter(p -> p.getMovementDescription().equals(movement.getMovementDescription())).findFirst().orElse(null);
        
            if (movementListResponse1 == null){
                // List<MovementTableResponse> tableResponse = new java.util.ArrayList<>();
                // MovementTableResponse tableResponse1 = new MovementTableResponse();
                movementListResponse1 = new MovementListResponse();
                movementListResponse1.setHigherAccountId(movement.getHigherAccountId());
                movementListResponse1.setAuxiliaryId(movement.getAuxiliaryId());
                movementListResponse1.setMovementDate(movement.getMovementDate());
                movementListResponse1.setCostCenterId(movement.getCostCenterId());
                movementListResponse1.setMovementDescription(movement.getMovementDescription());
                movementListResponse1.setId(movement.getId());
                // tableResponse.add(tableResponse1);
                // Parse voucherAmount which may contain commas and decimals, e.g. "-163,072,118.23"
                // beforeCredit[0] += movement.getVoucherNumber();
                
                // cuentasUnicas.forEach(cuentaUnica -> {
                    // if(movement.getHigherAccountId().getId().equals(cuentaUnica)){
                        // }
                        // });
                        // movementListResponse1.setMovementsList(tableResponse);
                        // MovementListResponse expects Longs; convert by rounding to nearest whole unit
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
                            // beforeDebit[0] = beforeDebit[0].add(amount);
                            movementListResponse1.setCredit(0L);
                            movementListResponse1.setDebit(amount.setScale(0, java.math.RoundingMode.HALF_UP).longValue());
                        } else{
                            // beforeCredit[0] = beforeCredit[0].add(amount);
                            movementListResponse1.setCredit(amount.setScale(0, java.math.RoundingMode.HALF_UP).longValue());
                            movementListResponse1.setDebit(0L);
                        }
                        movementListResponse1.setBalance(movementListResponse1.getDebit() + movementListResponse1.getCredit());
                        movementListResponse.add(movementListResponse1);
            }
            else{
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
                    // beforeDebit[0] = beforeDebit[0].add(amount);
                    movementListResponse1.setDebit(movementListResponse1.getDebit() + amount.setScale(0, java.math.RoundingMode.HALF_UP).longValue());
                } else{
                    // beforeCredit[0] = beforeCredit[0].add(amount);
                    movementListResponse1.setCredit(movementListResponse1.getCredit() + amount.setScale(0, java.math.RoundingMode.HALF_UP).longValue());
                }
                movementListResponse1.setBalance(movementListResponse1.getDebit() + movementListResponse1.getCredit());
            }

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
        Movements movements = MovementsRepositoriy.findById(id).orElse(null);
        try {
            String url = movements.getPoContractId()
                          .getFileId()
                          .getFileUrl()
                          .trim();
            InputStream is = new URL(url).openStream();
            byte[] pdfBytes = is.readAllBytes();

            movements.getPoContractId()
                    .getFileId()
                    .setFileUrl(Base64.getEncoder().encodeToString(pdfBytes));

        } catch (IOException e) {
        }
        return movements;
    }
}
