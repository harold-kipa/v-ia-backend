package com.v_ia_backend.kipa.dto.request;

import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

@Data
public class MovementFilesRequest {

     private Long accountNumberHomologated;

    private List<Long> ids;


}
