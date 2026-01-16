package com.v_ia_backend.kipa.dto.response;

import com.v_ia_backend.kipa.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementTableResponse {
    private Long id;
    private HigherAccounts higherAccountId;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal balance;
    private List<MovementListResponse> movementListResponse;

    public MovementTableResponse(Movements movements, BigDecimal debit, BigDecimal credit, BigDecimal balance, List<MovementListResponse> movementListResponse) {
        this.id = movements.getId();
        this.higherAccountId = movements.getHigherAccountId();
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
        this.movementListResponse = movementListResponse;
    }
    public MovementTableResponse() {

    }
}