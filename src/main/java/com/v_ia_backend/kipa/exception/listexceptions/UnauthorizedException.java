package com.avaluarApp.rti.exception.listexceptions;

import lombok.Data;

@Data
public class UnauthorizedException extends RuntimeException{
    private static final String DESCRIPTION = "Bad request exception (400)";

    public UnauthorizedException(String detail) {super(DESCRIPTION + ". " + detail); }
}
