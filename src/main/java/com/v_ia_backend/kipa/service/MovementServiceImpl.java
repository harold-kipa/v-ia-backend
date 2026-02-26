package com.v_ia_backend.kipa.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.dto.response.CapexResponse;
import com.v_ia_backend.kipa.dto.response.MovementListResponse;
import com.v_ia_backend.kipa.dto.response.MovementResponse;
import com.v_ia_backend.kipa.dto.response.MovementTableResponse;
import com.v_ia_backend.kipa.dto.response.MovementTotalsResponse;
import com.v_ia_backend.kipa.dto.response.OpexResponse;
import com.v_ia_backend.kipa.entity.FilesOp;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.HigherAccountsView;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.entity.PaymentsAccountsRelation;
import com.v_ia_backend.kipa.interfase.HigherAccountInterfase;
import com.v_ia_backend.kipa.interfase.MovementsFilesInterfase;
import com.v_ia_backend.kipa.interfase.MovementsInterfase;
import com.v_ia_backend.kipa.interfase.MovementsYearInterfase;
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
    public MovementTotalsResponse getAllMovementsByFilter(MovementFilterRequest movementFilterRequest) {
        List<MovementsInterfase> movements = new ArrayList<>();
        List<MovementsInterfase> movementsBefore = new ArrayList<>();
        Timestamp initialDate = Timestamp.valueOf("2015-01-01 05:00:00");
        Long higherAcountChange = 3643L;
        if(movementFilterRequest.getStartDate() != null || movementFilterRequest.getEndDate() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(movementFilterRequest.getStartDate().getTime());
            
            // Establecer el primer día del año
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            
            initialDate = new Timestamp(calendar.getTimeInMillis());
        }
        Timestamp initialProjectDate = Timestamp.valueOf("2015-01-01 05:00:00");

        if(movementFilterRequest.getInitialAccountId() != null && movementFilterRequest.getFinalAccountId() != null){
            movementFilterRequest.setInitialAccountId(higherAccountServiceImpl.getHigherAccountByHigherAccountsViewId(movementFilterRequest.getInitialAccountId()).getId());
            List<HigherAccounts> finalAccounts = higherAccountServiceImpl.getAllHigherAccountByHigherAccountsViewId(movementFilterRequest.getFinalAccountId());
            if (finalAccounts.size() > 1){
                movementFilterRequest.setFinalAccountId(finalAccounts.get(finalAccounts.size() - 1).getId());
            } else {
                movementFilterRequest.setFinalAccountId(finalAccounts.get(0).getId());
            }
        }

        // if(movementFilterRequest.getInitialAccountId() > higherAcountChange && movementFilterRequest.getFinalAccountId() < higherAcountChange){
            
        // }

        if(movementFilterRequest.getPaymentsAccountsRelationId() != null && movementFilterRequest.getInitialAccountId() != null && movementFilterRequest.getFinalAccountId() != null && movementFilterRequest.getStartDate() != null && movementFilterRequest.getEndDate() != null){
            List<PaymentsAccountsRelation> relations =
                paymentsAccountsRelationServiceImpl
                    .getPaymentsAccountsRelationByConsecutiveNumber(
                        movementFilterRequest.getPaymentsAccountsRelationId().toString()
                    );

            for (PaymentsAccountsRelation par : relations) {
                movements.addAll(
                    MovementsRepositoriy
                        .findDistinctByMovementDateBetweenAndHigherAccountId_IdBetweenAndPaymentsAccountsRelationId_Id(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId(), par.getId())
                );
            }
            // movements = MovementsRepositoriy.findByMovementDateBetweenAndHigherAccountId_IdBetweenAndPaymentsAccountsRelationId_Id(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId(), movementFilterRequest.getPoContractId());
        }
        
        else if (movementFilterRequest.getPoContractId() != null) {

            movements = MovementsRepositoriy
                .findDistinctByPoContractId_Id(movementFilterRequest.getPoContractId());

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
                        .findDistinctByPaymentsAccountsRelationId_Id(par.getId())
                );
            }
        }
        else if(movementFilterRequest.getDocumentNumber() != null){
            PaymentsAccountsRelation paymentsAccountsRelation = paymentsAccountsRelationServiceImpl.getPaymentsAccountsRelationById(movementFilterRequest.getDocumentNumber());
            movements = this.MovementsRepositoriy.findDistinctByPaymentsAccountsRelationId_Id(paymentsAccountsRelation.getId());
        }
        else if(movementFilterRequest.getAuxiliaryId() == null && movementFilterRequest.getInitialAccountId() == null && movementFilterRequest.getFinalAccountId() == null){
            movementsBefore = this.MovementsRepositoriy.findDistinctByMovementDateBetween(initialDate, movementFilterRequest.getStartDate());
            movements = this.MovementsRepositoriy.findDistinctByMovementDateBetween(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate());
        }
        else if(movementFilterRequest.getAuxiliaryId() == null && movementFilterRequest.getStartDate() == null && movementFilterRequest.getEndDate() == null){
            movements = this.MovementsRepositoriy.findDistinctByHigherAccountId_IdBetween(movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId());
        }
        else if(movementFilterRequest.getAuxiliaryId() != null && movementFilterRequest.getStartDate() != null && movementFilterRequest.getEndDate() != null && movementFilterRequest.getInitialAccountId() == null){
            movementsBefore = this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndAuxiliaryId_Id(initialDate, movementFilterRequest.getStartDate(), movementFilterRequest.getAuxiliaryId());
            movements = this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndAuxiliaryId_Id(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getAuxiliaryId());
        }
        else if(movementFilterRequest.getAuxiliaryId() == null ){
            if(movementFilterRequest.getInitialAccountId() < higherAcountChange){
                Instant instant = movementFilterRequest.getStartDate().toInstant();
                Instant adjusted = instant.minus(Duration.ofHours(6));
                Timestamp newTimestamp = Timestamp.from(adjusted);
                movementsBefore = this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndHigherAccountId_IdBetween(initialProjectDate, newTimestamp, movementFilterRequest.getInitialAccountId(), higherAcountChange);
                System.out.println(movementFilterRequest.getStartDate());
                // movementsBefore.addAll(this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndHigherAccountId_IdBetween(initialDate, movementFilterRequest.getStartDate(), higherAcountChange+1, movementFilterRequest.getFinalAccountId()));
            }
            else{
                Instant instant = movementFilterRequest.getStartDate().toInstant();
                Instant adjusted = instant.minus(Duration.ofHours(6));
                Timestamp newTimestamp = Timestamp.from(adjusted);
                movementsBefore = this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndHigherAccountId_IdBetween(initialDate, newTimestamp, movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId());
                
            }
            Instant instant = movementFilterRequest.getStartDate().toInstant();
            Instant adjusted = instant.minus(Duration.ofHours(6));
            Timestamp newTimestamp = Timestamp.from(adjusted);
            movements = this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndHigherAccountId_IdBetween(newTimestamp, movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId());
            System.out.println(movementFilterRequest.getStartDate());
            System.out.println(newTimestamp);
            System.out.println(initialDate);
            System.out.println(movementsBefore);
        }
        else if(movementFilterRequest.getInitialAccountId() == null && movementFilterRequest.getFinalAccountId() == null && movementFilterRequest.getStartDate() == null && movementFilterRequest.getEndDate() == null){
            movements = this.MovementsRepositoriy.findDistinctByAuxiliaryId_Id(movementFilterRequest.getAuxiliaryId());
        }
        else{
            movementsBefore = this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndHigherAccountId_IdBetweenAndAuxiliaryId_Id(initialDate, movementFilterRequest.getStartDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId(), movementFilterRequest.getAuxiliaryId());
            movements = this.MovementsRepositoriy.findDistinctByMovementDateBetweenAndHigherAccountId_IdBetweenAndAuxiliaryId_Id(movementFilterRequest.getStartDate(), movementFilterRequest.getEndDate(), movementFilterRequest.getInitialAccountId(), movementFilterRequest.getFinalAccountId(), movementFilterRequest.getAuxiliaryId());
        }
        movements = removeDuplicatesById(movements);

        movements.sort(
            Comparator.comparing(MovementsInterfase::getMovementDescription)
            .thenComparing(
                (MovementsInterfase m) -> m.getHigherAccountId(),
                Comparator.nullsLast(
                Comparator.comparing(HigherAccounts::getId)
                )
            )
        );
        System.out.println(movements.size());
        List<Long> cuentasUnicas = movements.stream()
                .map(MovementsInterfase::getHigherAccountId)
                .filter(Objects::nonNull)
                .map(HigherAccounts::getHigherAccountsViewId)
                .filter(Objects::nonNull)
                .map(HigherAccountsView::getId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(cuentasUnicas);

        MovementTotalsResponse responses = new MovementTotalsResponse();
        
        if (movementsBefore.isEmpty()==false){
            movementsBefore = removeDuplicatesById(movementsBefore);
            List<MovementsInterfase> movementBeforeCount = new ArrayList<>();
            for (MovementsInterfase movementBefore : movementsBefore) {
                if(movementBefore.getHigherAccountId().getId() == 201L){
                    System.out.println(movementBefore.getId());
                    System.out.println(movementBefore.getVoucherAmount());
                    movementBeforeCount.add(movementBefore);
                }
            }
            System.out.println(movementBeforeCount.size());
            
            List<Long> cuentasUnicasBefore = movementsBefore.stream()
                    .map(m -> m.getHigherAccountId().getHigherAccountsViewId().getId())   // <- Obtienes el Long
                    .distinct()
                    .collect(Collectors.toList());
    
            System.out.println(cuentasUnicasBefore);
            List<MovementListResponse> movementBeforeListResponse = sortMovements(movementsBefore);
            MovementTotalsResponse responsesBefore = calculationsMovements(movementBeforeListResponse, cuentasUnicasBefore);
            List<MovementListResponse> movementListResponse = sortMovements(movements);
            responses = calculationsBeforeMovements(movementListResponse, cuentasUnicas, responsesBefore.getMovementTableResponse());
        }
        else{
            List<MovementListResponse> movementListResponse = sortMovements(movements);
            responses = calculationsMovements(movementListResponse, cuentasUnicas);
        }
        

        // ajustar saldos iniciales

        return responses;
    }

    @Override
    public List<CapexResponse> getMovementByCapex(Long year) {
        Timestamp initialDate = Timestamp.valueOf("2015-01-01 05:00:00");
        Timestamp finalDate = Timestamp.valueOf(year + "-12-12 05:00:00");
        // List<MovementsYearInterfase> movementsInterfase = new ArrayList<>();
        List<HigherAccountInterfase> higherAccounts = higherAccountServiceImpl.getAllHigherAccountsCapex("capex");

        List<Long> higherAccountIds = higherAccounts.stream()
        .filter(h -> h.getInvestmentTarget() != null)
        .map(HigherAccountInterfase::getId)
        .toList();

        System.out.println(higherAccountIds);

        List<MovementsYearInterfase> movementsInterfase = MovementsRepositoriy
            .findByMovementDateBetweenAndHigherAccountId_IdIn(
                initialDate,
                finalDate,
                higherAccountIds
            );
        // for (HigherAccounts higherAccount : higherAccounts) {
        List<CapexResponse> capexResponseFinal = new java.util.ArrayList<>();
        for (MovementsYearInterfase movement : movementsInterfase) {
            CapexResponse capexResponse = capexResponseFinal.stream().filter(p -> p.getHigherAccountsId().getExcecutionClasification().equals(movement.getHigherAccountId().getExcecutionClasification())).findFirst().orElse(null);
            if(capexResponse == null){
                capexResponse = new CapexResponse();
                capexResponse.setHigherAccountsId(movement.getHigherAccountId());
                capexResponse.setCapexTotal(stringToLong(movement.getVoucherAmount()));
                capexResponse.setYear(movement.getMovementDate().toLocalDateTime().getYear());
                capexResponseFinal.add(capexResponse);
            }
            else{
                capexResponse.setCapexTotal(capexResponse.getCapexTotal().add(stringToLong((movement.getVoucherAmount()))));
            }
        }
        // }
        return capexResponseFinal;
    }

    @Override
    public List<CapexResponse> getMovementByOpex(Long year) {
        Timestamp initialDate = Timestamp.valueOf("2015-01-01 05:00:00");
        Timestamp finalDate = Timestamp.valueOf(year + "-12-12 05:00:00");
        // List<MovementsYearInterfase> movementsInterfase = new ArrayList<>();
        List<HigherAccountInterfase> higherAccounts = higherAccountServiceImpl.getAllHigherAccountsCapex("opex");

        List<Long> higherAccountIds = higherAccounts.stream()
        .filter(h -> h.getInvestmentTarget() != null)
        .map(HigherAccountInterfase::getId)
        .toList();

        System.out.println(higherAccountIds);

        List<MovementsYearInterfase> movementsInterfase = MovementsRepositoriy
            .findByMovementDateBetweenAndHigherAccountId_IdIn(
                initialDate,
                finalDate,
                higherAccountIds
            );
        // for (HigherAccounts higherAccount : higherAccounts) {
        List<CapexResponse> capexResponseFinal = new java.util.ArrayList<>();
        for (MovementsYearInterfase movement : movementsInterfase) {
            CapexResponse capexResponse = capexResponseFinal.stream().filter(p -> p.getHigherAccountsId().getExcecutionClasification().equals(movement.getHigherAccountId().getExcecutionClasification())).findFirst().orElse(null);
            if(capexResponse == null){
                capexResponse = new CapexResponse();
                capexResponse.setHigherAccountsId(movement.getHigherAccountId());
                capexResponse.setCapexTotal(stringToLong(movement.getVoucherAmount()));
                capexResponse.setYear(movement.getMovementDate().toLocalDateTime().getYear());
                capexResponseFinal.add(capexResponse);
            }
            else{
                capexResponse.setCapexTotal(capexResponse.getCapexTotal().add(stringToLong((movement.getVoucherAmount()))));
            }
        }
        // }
        return capexResponseFinal;
    }

    @Override
    public List<MovementsFilesInterfase> getAllFilesByMovements(List<Long> movementIds) {
        List<MovementsFilesInterfase> movementsFilesInterfase = MovementsRepositoriy.findByIdIn(movementIds);
        movementsFilesInterfase.forEach( movementFileInterfase -> {
            List<FilesOp> filesOp = filesOpServiceImpl.getFilesOpByPaymentsAccountsRelationId(movementFileInterfase.getPaymentsAccountsRelationId_Id());
            List<FilesOp> filesOpFinal = new java.util.ArrayList<>();
            for (FilesOp file : filesOp) {
                
                String url = file.getFileUrl();
    
                if (url == null || url.trim().isEmpty()) {
                    continue;
                }
    
                try (InputStream is = new URL(url.trim()).openStream()) {
                    byte[] pdfBytes = is.readAllBytes();
                    file.setFileUrl(Base64.getEncoder().encodeToString(pdfBytes)); // mejor: usar otro campo
                    filesOpFinal.add(file);
                } catch (IOException e) {
                }
            }
        });
        return movementsFilesInterfase;
    }

    @Override
    public MovementResponse getMovementById(Long id) {
        Movements movements = MovementsRepositoriy.findById(id).orElse(null);
        MovementResponse response = new MovementResponse();
        response.setMovements(movements);
        if (movements.getPoContractId() == null || movements.getPoContractId().getFileId() == null ) {
            List<FilesOp> filesOp = filesOpServiceImpl.getFilesOpByPaymentsAccountsRelationId(response.getMovements().getPaymentsAccountsRelationId().getId());
            List<FilesOp> filesOpFinal = new java.util.ArrayList<>();
            for (FilesOp file : filesOp) {
                
                String url = file.getFileUrl();

                if (url == null || url.trim().isEmpty()) {
                    continue;
                }

                try (InputStream is = new URL(url.trim()).openStream()) {
                    byte[] pdfBytes = is.readAllBytes();
                    file.setFileUrl(Base64.getEncoder().encodeToString(pdfBytes)); // mejor: usar otro campo
                    filesOpFinal.add(file);
                } catch (IOException e) {
                }
            }
            response.setFilesOp(filesOpFinal);
            // return response;
            try {
                for (int i = 0; i < response.getFilesOp().size(); i++) {
                    String url = response.getFilesOp().get(i).getFileUrl()
                                  .trim()
                                  .replace(" ", "%20");
                    InputStream is = new URL(url).openStream();
                    byte[] pdfBytes = is.readAllBytes();
        
                    response.getFilesOp().get(i)
                            .setFileUrl(Base64.getEncoder().encodeToString(pdfBytes));
                }
    
            } catch (IOException e) {
                System.out.println("Error al obtener el archivo PDF: " + e.getMessage());
            }
        }
        else{
            if (response.getMovements() != null
                    && response.getMovements().getPaymentsAccountsRelationId() != null
                    && response.getMovements().getPaymentsAccountsRelationId().getId() != null) {
                List<FilesOp> filesOp = filesOpServiceImpl.getFilesOpByPaymentsAccountsRelationId(response.getMovements().getPaymentsAccountsRelationId().getId());
                List<FilesOp> filesOpFinal = new java.util.ArrayList<>();
                for (FilesOp file : filesOp) {
                    
                    String url = file.getFileUrl();
    
                    if (url == null || url.trim().isEmpty()) {
                        continue;
                    }
    
                    try (InputStream is = new URL(url.trim()).openStream()) {
                        byte[] pdfBytes = is.readAllBytes();
                        file.setFileUrl(Base64.getEncoder().encodeToString(pdfBytes)); // mejor: usar otro campo
                        filesOpFinal.add(file);
                    } catch (IOException e) {
                    }
                }
                response.setFilesOp(filesOpFinal);
                // return response;
                try {
                    for (int i = 0; i < response.getFilesOp().size(); i++) {
                        String url = response.getFilesOp().get(i).getFileUrl()
                                      .trim()
                                      .replace(" ", "%20");
                        InputStream is = new URL(url).openStream();
                        byte[] pdfBytes = is.readAllBytes();
            
                        response.getFilesOp().get(i)
                                .setFileUrl(Base64.getEncoder().encodeToString(pdfBytes));
                    }
        
                } catch (IOException e) {
                    System.out.println("Error al obtener el archivo PDF: " + e.getMessage());
                }
            }
            try {
                String url = response.getMovements().getPoContractId().getFileId().getFileUrl()
                                .trim()
                                .replace(" ", "%20");
                InputStream is = new URL(url).openStream();
                byte[] pdfBytes = is.readAllBytes();
    
                response.getMovements().getPoContractId().getFileId()
                        .setFileUrl(Base64.getEncoder().encodeToString(pdfBytes));
    
            } catch (IOException e) {
                System.out.println("Error al obtener el archivo PDF: " + e.getMessage());
            }
        }

        
        return response;
    }

    public BigDecimal stringToLong(String raw){
        java.math.BigDecimal amount = java.math.BigDecimal.ZERO;
        if(raw != null && !raw.isBlank()){
            // Normalize: remove grouping separators and trim
            String cleaned = raw.replaceAll("[,\\s]", "");
            try{
                return new java.math.BigDecimal(cleaned);
            } catch (NumberFormatException ex){
                // If parsing fails, fallback to zero to avoid crashing; log the problem
                System.err.println("Failed parsing voucherAmount");
                return java.math.BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;

    }
    public List<MovementListResponse> sortMovements(List<MovementsInterfase> movements){
        List<MovementListResponse> movementListResponse = new java.util.ArrayList<>();
        movements.forEach(movement -> {
            // filtro que retorna un movimiento si encuentra uno repetido
            MovementListResponse movementListResponse1 = movementListResponse.stream().filter(p -> p.getMovementDescription().equals(movement.getMovementDescription()) && p.getHigherAccountId().getId().equals(movement.getHigherAccountId().getId()) && p.getAuxiliaryId().getId().equals(movement.getAuxiliaryId().getId())).findFirst().orElse(null);
            // si es nuevo, lo crea
            if (movementListResponse1 == null){
                if (movement.getMovementDescription().equals("CANCELA CUENTAS POR CIERRE ANUAL")){ 
                    return;
                }
                if (movement.getMovementDescription().equals("SALDOS INICIALES")){ 
                    return;
                }
                // List<MovementTableResponse> tableResponse = new java.util.ArrayList<>();
                // MovementTableResponse tableResponse1 = new MovementTableResponse();
                movementListResponse1 = new MovementListResponse();
                movementListResponse1.setMovementDescription(movement.getMovementDescription());
                movementListResponse1.setHigherAccountId(movement.getHigherAccountId());
                movementListResponse1.setAuxiliaryId(movement.getAuxiliaryId());
                movementListResponse1.setMovementDate(movement.getMovementDate());
                movementListResponse1.setCostCenterId(movement.getCostCenterId());
                movementListResponse1.setId(movement.getId());
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
                    movementListResponse1.setCredit(java.math.BigDecimal.ZERO);
                    movementListResponse1.setDebit(amount.setScale(0, java.math.RoundingMode.HALF_UP));
                } else{
                    // beforeCredit[0] = beforeCredit[0].add(amount);
                    movementListResponse1.setCredit(amount.setScale(0, java.math.RoundingMode.HALF_UP));
                    movementListResponse1.setDebit(java.math.BigDecimal.ZERO);
                }
                movementListResponse1.setBalance(movementListResponse1.getDebit().add(movementListResponse1.getCredit()));
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

                    movementListResponse1.setDebit(movementListResponse1.getDebit().add(amount));
                } else{
                    movementListResponse1.setCredit(movementListResponse1.getCredit().add(amount));
                }
                
            }

            // movementListResponse1.setBalance(BigDecimal.ZERO.longValue());
        });
        return movementListResponse;
    }

    public MovementTotalsResponse calculationsMovements(List<MovementListResponse> movementListResponse, List<Long> cuentasUnicas){
        MovementTotalsResponse movementTotalsResponse = new MovementTotalsResponse(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO, new ArrayList<>());
        List<MovementTableResponse> responses = new ArrayList<>();
        cuentasUnicas.forEach(cuentaUnica -> {
            List<MovementListResponse> movementListResponse1 = new ArrayList<>();
            movementListResponse.forEach(movement -> {
                if(movement.getHigherAccountId().getHigherAccountsViewId().getId().equals(cuentaUnica)){
                    movementListResponse1.add(movement);
                }
            });
            MovementTableResponse tableResponse = new MovementTableResponse();
            if (movementListResponse1 != null && !movementListResponse1.isEmpty()) {
                tableResponse.setHigherAccountId(movementListResponse1.get(0).getHigherAccountId());
                if(tableResponse.getHigherAccountId().getId()==201L){
                        System.out.println(tableResponse.getId());
                    }
                BigDecimal[] totals = {BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO}; // [totalDebit, totalCredit, totalBalance]
                movementListResponse1.forEach(movement -> {
                    movement.setDebit(movement.getDebit().abs());
                    movement.setCredit(movement.getCredit().abs());
                    totals[0] = totals[0].add(movement.getDebit());
                    totals[1] = totals[1].add(movement.getCredit());
                    String cuentaUnicaStr = String.valueOf(movement.getHigherAccountId().getAccountNumberHomologated());
                    if (cuentaUnicaStr != null && (cuentaUnicaStr.startsWith("1") 
                        || cuentaUnicaStr.startsWith("5") 
                        || cuentaUnicaStr.startsWith("6"))) {
                            movement.setBalance(movement.getDebit().subtract(movement.getCredit()));
                    } else {
                        movement.setBalance(movement.getCredit().subtract(movement.getDebit()));
                    }
                    movement.setBalance(movement.getBalance().add(totals[2]));
                    totals[2] = movement.getBalance();
                });
                tableResponse.setId(movementListResponse1.get(0).getId());
                tableResponse.setDebit(totals[0]);
                tableResponse.setCredit(totals[1]);
                tableResponse.setBalance(movementListResponse1.get(movementListResponse1.size() - 1).getBalance());
                tableResponse.setMovementListResponse(movementListResponse1);
                responses.add(tableResponse);
                movementTotalsResponse.setTotalDebit(movementTotalsResponse.getTotalDebit().add(totals[0]));
                movementTotalsResponse.setTotalCredit(movementTotalsResponse.getTotalCredit().add(totals[1]));
                movementTotalsResponse.setTotalBalance(movementTotalsResponse.getTotalBalance().add(movementListResponse1.get(movementListResponse1.size() - 1).getBalance()));
            } 
        });


        movementTotalsResponse.setMovementTableResponse(responses);
        return movementTotalsResponse;
    }


    public MovementTotalsResponse calculationsBeforeMovements(List<MovementListResponse> movementListResponse, List<Long> cuentasUnicas, List<MovementTableResponse> movementListResponseBefore){
        MovementTotalsResponse movementTotalsResponse = new MovementTotalsResponse(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO, new ArrayList<>());
        List<MovementTableResponse> responses = new ArrayList<>();
        cuentasUnicas.forEach(cuentaUnica -> {
            List<MovementListResponse> movementListResponse1 = new ArrayList<>();
            // saldo inicial
            movementListResponseBefore.forEach(movement -> {
                if(movement.getHigherAccountId().getHigherAccountsViewId().getId().equals(cuentaUnica)){
                    
                    MovementListResponse tableResponseInit = new MovementListResponse();
                    tableResponseInit.setId(0L);
                    tableResponseInit.setHigherAccountId(movement.getHigherAccountId());
                    tableResponseInit.setMovementDescription("SALDO INICIAL");
                    tableResponseInit.setDebit(movement.getDebit());
                    tableResponseInit.setCredit(movement.getCredit());
                    tableResponseInit.setBalance(movement.getBalance());
                    movementListResponse1.add(tableResponseInit);
                }
            });
            movementListResponse.forEach(movement -> {
                if(movement.getHigherAccountId().getHigherAccountsViewId().getId().equals(cuentaUnica)){
                    movementListResponse1.add(movement);
                }
            });
            MovementTableResponse tableResponse = new MovementTableResponse();
            if (movementListResponse1 != null && !movementListResponse1.isEmpty()) {

                tableResponse.setHigherAccountId(movementListResponse1.get(0).getHigherAccountId());
                BigDecimal[] totals = {BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO}; // [totalDebit, totalCredit, totalBalance]
                movementListResponse1.forEach(movement -> {
                    movement.setDebit(movement.getDebit().abs());
                    movement.setCredit(movement.getCredit().abs());
                    totals[0] = totals[0].add(movement.getDebit());
                    totals[1] = totals[1].add(movement.getCredit());
                    String cuentaUnicaStr = String.valueOf(movement.getHigherAccountId().getAccountNumberHomologated());
                    if (cuentaUnicaStr != null && (cuentaUnicaStr.startsWith("1") 
                        || cuentaUnicaStr.startsWith("5") 
                        || cuentaUnicaStr.startsWith("6"))) {
                            movement.setBalance(movement.getDebit().subtract(movement.getCredit()));
                    } else {
                        movement.setBalance(movement.getCredit().subtract(movement.getDebit()));
                    }
                    movement.setBalance(movement.getBalance().add(totals[2]));
                    totals[2] = movement.getBalance();
                });
                if(movementListResponse1.size()>1){
                    tableResponse.setId(movementListResponse1.get(1).getId());
                } else {
                    tableResponse.setId(movementListResponse1.get(0).getId());
                }
                tableResponse.setDebit(totals[0]);
                tableResponse.setCredit(totals[1]);
                tableResponse.setBalance(movementListResponse1.get(movementListResponse1.size() - 1).getBalance());
                tableResponse.setMovementListResponse(movementListResponse1);
                responses.add(tableResponse);
                movementTotalsResponse.setTotalDebit(movementTotalsResponse.getTotalDebit().add(totals[0]));
                movementTotalsResponse.setTotalCredit(movementTotalsResponse.getTotalCredit().add(totals[1]));
                movementTotalsResponse.setTotalBalance(movementTotalsResponse.getTotalBalance().add(movementListResponse1.get(movementListResponse1.size() - 1).getBalance()));
            } 
        });

        movementTotalsResponse.setMovementTableResponse(responses);
        return movementTotalsResponse;
    }

    public List<MovementsInterfase> removeDuplicatesById(List<MovementsInterfase> movements) {

        Set<Long> seenIds = new HashSet<>();

        return movements.stream()
                .filter(m -> seenIds.add(m.getId()))
                .collect(Collectors.toList());
    }

}
