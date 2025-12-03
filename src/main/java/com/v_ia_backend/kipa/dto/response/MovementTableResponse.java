package com.v_ia_backend.kipa.dto.response;

import com.v_ia_backend.kipa.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementTableResponse {

    private Long id;
    private Timestamp movementDate;
    private HigherAccounts higherAccountId;
    private Auxiliaries auxiliaryId;
    private CostCenters costCenterId;
    private String movementDescription;

    public MovementTableResponse(Movements movements) {
        this.id = movements.getId();
        this.movementDate = movements.getMovementDate();
        this.higherAccountId = movements.getHigherAccountId();
        this.auxiliaryId = movements.getAuxiliaryId();
        this.costCenterId = movements.getCostCenterId();
        this.movementDescription = movements.getMovementDescription();
    }
    public MovementTableResponse() {

    }
}