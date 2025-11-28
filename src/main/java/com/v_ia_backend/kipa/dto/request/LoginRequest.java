package com.v_ia_backend.kipa.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

@Data
public class    LoginRequest {

    private static final long serialVersionUID = 5926468583005150707L;

    @NotEmpty(message = "{notEmpty.email}")
    @Size(min = 8, max = 50, message = "{size.email}")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "{pattern.email}")
    private String email;

    @NotEmpty(message = "{notEmpty.Password}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&.])([A-Za-z\\d$@$!%*?&.]){8,15}$", message = "{pattern.Password}")
    private String password;

}
