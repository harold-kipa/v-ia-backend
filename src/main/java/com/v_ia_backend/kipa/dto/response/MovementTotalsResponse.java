package com.v_ia_backend.kipa.dto.response;

import com.v_ia_backend.kipa.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementTotalsResponse {
    private BigDecimal totalDebit;
    private BigDecimal totalCredit;
    private BigDecimal totalBalance;
    private List<MovementTableResponse> movementTableResponse;

    public MovementTotalsResponse(BigDecimal totalDebit, BigDecimal totalCredit, BigDecimal totalBalance, List<MovementTableResponse> movementTableResponse) {
        this.totalDebit = totalDebit;
        this.totalCredit = totalCredit;
        this.totalBalance = totalBalance;
        this.movementTableResponse = movementTableResponse;
    }
    public MovementTotalsResponse() {

    }
}