package com.v_ia_backend.kipa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;
import com.v_ia_backend.kipa.entity.Natures;
import com.v_ia_backend.kipa.interfase.MovementsInterfase;
import com.v_ia_backend.kipa.interfase.MovementsWithoutHigherAccountInterfase;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementsAndHigherAccountResponse {

    // private List <MovementTableResponse> movementsList;
    private Long id;
    private Timestamp movementDate;
    private HigherAccounts higherAccountId;
    private Auxiliaries auxiliaryId;  
    private CostCenters costCenterId;
    private Natures natureId;
    private String movementDescription;
    private String voucherAmount;


    public MovementsAndHigherAccountResponse(
        Long id,
        Timestamp movementDate,
        HigherAccounts higherAccountId,
        Auxiliaries auxiliaryId,
        CostCenters costCenterId,
        Natures natureId,
        String movementDescription,
        String voucherAmount
    ) {
        this.id = id;
        this.movementDate = movementDate;
        this.higherAccountId = higherAccountId;
        this.auxiliaryId = auxiliaryId;
        this.costCenterId = costCenterId;
        this.natureId = natureId;
        this.movementDescription = movementDescription;
        this.voucherAmount = voucherAmount;
    }
    public MovementsAndHigherAccountResponse() {

    }
}