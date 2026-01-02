package com.v_ia_backend.kipa.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.dto.response.MovementListResponse;
import com.v_ia_backend.kipa.dto.response.MovementResponse;
import com.v_ia_backend.kipa.dto.response.MovementTableResponse;
import com.v_ia_backend.kipa.entity.FilesOp;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.entity.PaymentsAccountsRelation;
import com.v_ia_backend.kipa.interfase.MovementsInterfase;
import com.v_ia_backend.kipa.repository.MovementsRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.MovementService;


@Service
public class MovementServiceImpl implements MovementService {
    private final MovementsRepositoriy MovementsRepositoriy;
    private final PaymentsAccountsRelationServiceImpl paymentsAccountsRelationServiceImpl;
    private final HigherAccountServiceImpl higherAccountServiceImpl;
    private final FilesOpServiceImpl filesOpServiceImpl;
    public record MovementGroupKey(Long higherAccountId, String movementDescription) {}
    public MovementServiceImpl(MovementsRepositoriy MovementsRepositoriy, PaymentsAccountsRelationServiceImpl paymentsAccountsRelationServiceImpl, HigherAccountServiceImpl higherAccountServiceImpl, FilesOpServiceImpl filesOpServiceImpl) {
        this.MovementsRepositoriy = MovementsRepositoriy;
        this.paymentsAccountsRelationServiceImpl = paymentsAccountsRelationServiceImpl;
        this.higherAccountServiceImpl = higherAccountServiceImpl;
        this.filesOpServiceImpl = filesOpServiceImpl;
    }

    @Override
    public List<MovementTableResponse> getAllMovementsByFilter(MovementFilterRequest movementFilterRequest) {
        List<MovementsInterfase> movements = new ArrayList<>();

        if(movementFilterRequest.getInitialAccountId() != null && movementFilterRequest.getFinalAccountId() != null){
            movementFilterRequest.setInitialAccountId(higherAccountServiceImpl.getHigherAccountByHigherAccountsViewId(movementFilterRequest.getInitialAccountId()).getId());
            movementFilterRequest.setFinalAccountId(higherAccountServiceImpl.getHigherAccountByHigherAccountsViewId(movementFilterRequest.getFinalAccountId()).getId());
        }
        
        if (movementFilterRequest.getPoContractId() != null) {

            movements = MovementsRepositoriy
                .findByPoContractId_Id(movementFilterRequest.getPoContractId());

        }
        else if (movementFilterRequest.getPaymentsAccountsRelationId() != null) {

            List<PaymentsAccountsRelation> relations =
                paymentsAccountsRelationServiceImpl
                    .getPaymentsAccountsRelationByConsecutiveNumber(
                        movementFilterRequest.getPaymentsAccountsRelationId().toString()
                    );

            for (PaymentsAccountsRelation par : relations) {
                movements.addAll(
                    MovementsRepositoriy
                        .findByPaymentsAccountsRelationId_Id(par.getId())
                );
            }
        }
        else if(movementFilterRequest.getDocumentNumber() != null){
            PaymentsAccountsRelation paymentsAccountsRelation = paymentsAccountsRelationServiceImpl.getPaymentsAccountsRelationById(movementFilterRequest.getDocumentNumber());
            movements = this.MovementsRepositoriy.findByPaymentsAccountsRelationId_Id(paymentsAccountsRelation.getId());
        }
        else if(movementFilterRequest.getAuxiliaryId() == null && movementFilterRequest.getInitialAccountId() == null && movementFilterRequest.getFinalAccountId() == null){
            movements = this.MovementsRepositoriy.findByMovementDateBetween(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate());
        }
        else if(movementFilterRequest.getAuxiliaryId() == null && movementFilterRequest.getInitialAccountId() == null && movementFilterRequest.getFinalAccountId() == null){
            movements = this.MovementsRepositoriy.findByMovementDateBetween(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate());
        }
        else if(movementFilterRequest.getAuxiliaryId() == null){
            movements = this.MovementsRepositoriy.findByMovementDateBetweenAndHigherAccountId_IdBetween(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId());
        }
        else if(movementFilterRequest.getInitialAccountId() == null && movementFilterRequest.getFinalAccountId() == null){
            movements = this.MovementsRepositoriy.findByAuxiliaryId_Id(movementFilterRequest.getAuxiliaryId());
        }
        else{
            movements = this.MovementsRepositoriy.findByMovementDateBetweenAndHigherAccountId_IdBetweenAndAuxiliaryId_Id(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId(), movementFilterRequest.getAuxiliaryId());
        }
        movements.sort(
            Comparator.comparing(MovementsInterfase::getMovementDescription)
            .thenComparing(
                (MovementsInterfase m) -> m.getHigherAccountId(),
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

        List<MovementListResponse> movementListResponse = new java.util.ArrayList<>();
        movements.forEach(movement -> {
            // filtro que retorna un movimiento si encuentra uno repetido
            MovementListResponse movementListResponse1 = movementListResponse.stream().filter(p -> p.getMovementDescription().equals(movement.getMovementDescription())).findFirst().orElse(null);
            // si es nuevo, lo crea
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
            // de lo contrario solo suma los valores
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

                    movementListResponse1.setDebit(movementListResponse1.getDebit() + amount.setScale(0, java.math.RoundingMode.HALF_UP).longValue());
                } else{
                    movementListResponse1.setCredit(movementListResponse1.getCredit() + amount.setScale(0, java.math.RoundingMode.HALF_UP).longValue());
                }
                
            }

            // movementListResponse1.setBalance(BigDecimal.ZERO.longValue());
        });

        List<MovementTableResponse> responses = new ArrayList<>();
        cuentasUnicas.forEach(cuentaUnica -> {
            List<MovementListResponse> movementListResponse1 = new ArrayList<>();
            movementListResponse.forEach(movement -> {
                if(movement.getHigherAccountId().getId().equals(cuentaUnica)){
                    movementListResponse1.add(movement);
                }
            });
            MovementTableResponse tableResponse = new MovementTableResponse();
            if (movementListResponse1 != null && !movementListResponse1.isEmpty()) {
                tableResponse.setHigherAccountId(movementListResponse1.get(0).getHigherAccountId());
                long[] totals = {0L, 0L, 0L}; // [totalDebit, totalCredit, totalBalance]
                movementListResponse1.forEach(movement -> {
                    movement.setDebit(Math.abs(movement.getDebit()));
                    movement.setCredit(Math.abs(movement.getCredit()));
                    totals[0] += Math.abs(movement.getDebit());
                    totals[1] += Math.abs(movement.getCredit());
                    String cuentaUnicaStr = String.valueOf(movement.getHigherAccountId().getAccountNumberHomologated());
                    if (cuentaUnicaStr != null && (cuentaUnicaStr.startsWith("1") 
                        || cuentaUnicaStr.startsWith("5") 
                        || cuentaUnicaStr.startsWith("6"))) {
                            movement.setBalance( movement.getDebit() - movement.getCredit());
                    } else {
                        movement.setBalance(- movement.getDebit() + movement.getCredit());
                    }
                    movement.setBalance(movement.getBalance() + totals[2]);
                    totals[2] = movement.getBalance();
                });
                tableResponse.setId(movementListResponse1.get(0).getId());
                tableResponse.setDebit(totals[0]);
                tableResponse.setCredit(totals[1]);
                tableResponse.setBalance(movementListResponse1.get(movementListResponse1.size() - 1).getBalance());
                tableResponse.setMovementListResponse(movementListResponse1);
                responses.add(tableResponse);
            } 
            // else {
            //     tableResponse.setHigherAccountId(null); // o el valor por defecto que tenga sentido
            //     tableResponse.setDebit(null);
            //     tableResponse.setCredit(null);
            //     tableResponse.setBalance(null);
            // }
            // tableResponse.setMovementListResponse(movementListResponse1);
            // responses.add(tableResponse);
        });


        return responses;
    }

    @Override
    public MovementResponse getMovementById(Long id) {
        Movements movements = MovementsRepositoriy.findById(id).orElse(null);
        MovementResponse response = new MovementResponse();
        response.setMovements(movements);
        if (movements.getPoContractId() == null || movements.getPoContractId().getFileId() == null ) {
            List<FilesOp> filesOp = filesOpServiceImpl.getFilesOpByPaymentsAccountsRelationId(response.getMovements().getPaymentsAccountsRelationId().getId());
            for (FilesOp file : filesOp) {
                String url = file.getFileUrl();

                if (url == null || url.trim().isEmpty()) {
                    continue;
                }

                try (InputStream is = new URL(url.trim()).openStream()) {
                    byte[] pdfBytes = is.readAllBytes();
                    file.setFileUrl(Base64.getEncoder().encodeToString(pdfBytes)); // mejor: usar otro campo
                } catch (IOException e) {
                }
            }
            response.setFilesOp(filesOp);
            return response;
        };
        try {
            String url = response.getMovements().getPoContractId()
                          .getFileId()
                          .getFileUrl()
                          .trim();
            InputStream is = new URL(url).openStream();
            byte[] pdfBytes = is.readAllBytes();

            response.getMovements().getPoContractId()
                    .getFileId()
                    .setFileUrl(Base64.getEncoder().encodeToString(pdfBytes));

        } catch (IOException e) {
        }

        
        return response;
    }
}
