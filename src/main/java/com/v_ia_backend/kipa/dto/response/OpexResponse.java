package com.v_ia_backend.kipa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.Movements;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class OpexResponse {

    // private List <RemunerationTableResponse> remunerationList;
    private Long auxiliaryId;
    private Long opexTotal;


    public OpexResponse(Movements movements, Long opexTotal) {
        this.auxiliaryId = movements.getAuxiliaryId().getId();
        this.opexTotal = opexTotal;
    }
    public OpexResponse() {

    }
}