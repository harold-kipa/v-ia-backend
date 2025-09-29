package com.v_ia_backend.kipa.dto.request;
import com.v_ia_backend.kipa.entity.Roles;
import com.v_ia_backend.kipa.entity.StatusUser;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

@Data
public class UsersRequest {

    @NotEmpty(message = "{notEmpty.email}")
    @Size(min = 8, max = 50, message = "{size.email}")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message ="{pattern.email}")
    private String email;

    @NotEmpty(message = "{notEmpty.Password}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&.])([A-Za-z\\d$@$!%*?&.]){8,15}$", message = "{pattern.Password}")
    private String password;

    @NotNull(message = "{notNull.general}")
    private StatusUser statusId;

    @NotNull(message = "{notNull.general}")
    private Roles roleId;   

    @NotEmpty(message = "{notEmpty.name}")
    @Size(min = 2, max = 50, message = "{size.name}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.name}")
    private String name;

    @NotEmpty(message = "{notEmpty.name}")
    @Size(min = 2, max = 50, message = "{size.name}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.name}")
    private String lastName;

    @NotNull(message = "{notNull.general}")
    private Long identification;

}
