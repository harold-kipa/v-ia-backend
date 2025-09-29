package com.v_ia_backend.kipa.exception.listexceptions;

import lombok.Data;

@Data
public class JwtException extends RuntimeException{
    private static final String DESCRIPTION = "Bad request exception (400)";

    public JwtException(String detail) {super(detail); }
}
