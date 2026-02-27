package com.v_ia_backend.kipa.dto.request;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MovementFilterRequest {

    private Timestamp startDate;

    private Timestamp endDate;
    
    private Long initialAccountId;

    private Long finalAccountId;

    private Long costCenterId;

    private Long auxiliaryId;

    private Long poContractId;

    private Long paymentsAccountsRelationId;

    private Long documentNumber;

}
