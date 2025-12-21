package com.v_ia_backend.kipa.dto.response;

import com.v_ia_backend.kipa.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementTableResponse {
    private HigherAccounts higherAccountId;
    private Long debit;    
    private Long credit;
    private Long balance;
    private List<MovementListResponse> movementListResponse;

    public MovementTableResponse(Movements movements, Long debit, Long credit, Long balance, List<MovementListResponse> movementListResponse) {
        this.higherAccountId = movements.getHigherAccountId();
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
        this.movementListResponse = movementListResponse;
    }
    public MovementTableResponse() {

    }
}