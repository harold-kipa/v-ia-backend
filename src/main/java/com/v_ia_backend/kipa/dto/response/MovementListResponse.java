package com.v_ia_backend.kipa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementListResponse {

    private List <MovementTableResponse> movementsList;
    private Long debit;
    private Long credit;
    private Long balance;

    public MovementListResponse(List <MovementTableResponse> movements, Long debit, Long credit, Long balance) {
        this.movementsList = movements;
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
    }
    public MovementListResponse() {

    }
}