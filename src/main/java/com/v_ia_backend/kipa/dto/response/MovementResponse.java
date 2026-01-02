package com.v_ia_backend.kipa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.entity.FilesOp;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Movements;

import lombok.Data;

import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MovementResponse {

    // private List <MovementTableResponse> movementsList;
    private Movements movements;
    private List<FilesOp> filesOp;


    public MovementResponse(Movements movements, List<FilesOp> filesOp) {
        this.movements = movements;
        this.filesOp = filesOp;
    }
    public MovementResponse() {

    }
}