package com.v_ia_backend.kipa.dto.response;

import com.v_ia_backend.kipa.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementArhResponse {
    private Long id;
    private ArhClasification arhClasificationId;
    private BigDecimal Debit;
    private BigDecimal Credit;
    private BigDecimal Balance;
    private List<MovementTableResponse> movementTableResponse;

    public MovementArhResponse(Long id, ArhClasification arhClasificationId, BigDecimal Debit, BigDecimal Credit, BigDecimal Balance, List<MovementTableResponse> movementTableResponse) {
        this.id = id;
        this.arhClasificationId = arhClasificationId;
        this.Debit = Debit;
        this.Credit = Credit;
        this.Balance = Balance;
        this.movementTableResponse = movementTableResponse;
    }
    public MovementArhResponse() {

    }
}