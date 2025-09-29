package com.v_ia_backend.kipa.exception.listexceptions;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad request exception (400)";

    public BadRequestException(String detail) {
        super(detail);
    }
}
