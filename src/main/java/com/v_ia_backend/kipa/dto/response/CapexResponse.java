package com.v_ia_backend.kipa.dto.response;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.interfase.HigherAccountInterfase;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CapexResponse {

    // private List <RemunerationTableResponse> remunerationList;
    private HigherAccountInterfase higherAccountsId;
    private BigDecimal capexTotal;
    private int year;


    public CapexResponse(HigherAccountInterfase higherAccountsId, BigDecimal capexTotal, int year) {
        this.higherAccountsId = higherAccountsId;
        this.capexTotal = capexTotal;
        this.year = year;

    }
    public CapexResponse() {

    }
}