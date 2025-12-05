package com.v_ia_backend.kipa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementListResponse {

    // private List <MovementTableResponse> movementsList;
    private Long id;
    private Timestamp movementDate;
    private HigherAccounts higherAccountId;
    private Auxiliaries auxiliaryId;
    private CostCenters costCenterId;
    private String voucherAmount;
    private String movementDescription;
    private Long debit;    
    private Long credit;
    private Long balance;


    public MovementListResponse(Movements movements, Long debit, Long credit, Long balance) {
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