package com.avaluarApp.rti.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {
    private String exception;
    private String message;

    public ErrorMessage(Exception exception, String message) {
        this.exception = exception.getClass().getSimpleName();
        this.message = message;
    }
}

