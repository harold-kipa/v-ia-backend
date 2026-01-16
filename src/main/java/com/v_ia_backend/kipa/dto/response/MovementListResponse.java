package com.v_ia_backend.kipa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementListResponse {

    // private List <MovementTableResponse> movementsList;
    private Long id;
    private Timestamp movementDate;
    private CostCenters costCenterId;
    private String voucherAmount;
    private String movementDescription;
    private Auxiliaries auxiliaryId;
    private HigherAccounts higherAccountId;
    private BigDecimal debit;    
    private BigDecimal credit;
    private BigDecimal balance;


    public MovementListResponse(Movements movements, BigDecimal debit, BigDecimal credit, BigDecimal balance) {
        this.id = movements.getId();
        this.movementDate = movements.getMovementDate();
        this.higherAccountId = movements.getHigherAccountId();
        this.auxiliaryId = movements.getAuxiliaryId();
        this.costCenterId = movements.getCostCenterId();
        this.movementDescription = movements.getMovementDescription();
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
    }
    public MovementListResponse() {

    }
}