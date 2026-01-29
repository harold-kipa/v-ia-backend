package com.v_ia_backend.kipa.dto.response;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.interfase.HigherAccountInterfase;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class AniSubaccountsResponse {

    // private List <RemunerationTableResponse> remunerationList;
    private String subaccountCodeName;
    private BigDecimal voucherAmount;
    private int year;


    public AniSubaccountsResponse(String subaccountCodeName, BigDecimal voucherAmount, int year) {
        this.subaccountCodeName = subaccountCodeName;
        this.voucherAmount = voucherAmount;
        this.year = year;

    }
    public AniSubaccountsResponse() {

    }
}