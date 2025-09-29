package com.v_ia_backend.kipa.exception.listexceptions;

import lombok.Data;

@Data
public class ConflictException extends RuntimeException{
    private static final String DESCRIPTION = "Conflict exception (409)";

    public ConflictException(String detail) {super(detail); }
}
