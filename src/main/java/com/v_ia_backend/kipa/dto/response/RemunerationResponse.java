package com.v_ia_backend.kipa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.FilesOp;
import com.v_ia_backend.kipa.entity.Remuneration;

import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class RemunerationResponse {

    // private List <RemunerationTableResponse> remunerationList;
    private int year;
    private Long compensationPaidValue;
    private Long liquidationBaseIncome;


    public RemunerationResponse(int year, Long compensationPaidValue, Long liquidationBaseIncome) {
        this.year = year;
        this.compensationPaidValue = compensationPaidValue;
        this.liquidationBaseIncome = liquidationBaseIncome;
    }
    public RemunerationResponse() {

    }
}