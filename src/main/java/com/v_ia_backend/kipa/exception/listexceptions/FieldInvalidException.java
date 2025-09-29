package com.avaluarApp.rti.exception.listexceptions;

import lombok.Data;

@Data
public class FieldInvalidException extends RuntimeException{
    private static final String DESCRIPTION = "Field Invalid Exception (417)";

    public FieldInvalidException(String detail) {super( detail); }
}
