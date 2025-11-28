package com.v_ia_backend.kipa.dto.request;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

@Data
public class MovementFilterRequest {

    private Timestamp startDate;

    private Timestamp endDate;
    
    private Long initialAccountId;

    private Long finalAccountId;

    private Long costCenterId;

    private Long auxiliaryId;

}
