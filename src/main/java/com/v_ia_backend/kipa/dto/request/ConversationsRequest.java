package com.v_ia_backend.kipa.dto.request;
import com.v_ia_backend.kipa.entity.Roles;
import com.v_ia_backend.kipa.entity.StatusUser;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

@Data
public class ConversationsRequest {

    @NotEmpty(message = "{notNull.general}")
    private String from;

    @NotEmpty(message = "{notNull.general}")
    private String text;

    @NotEmpty(message = "{notNull.general}")
    private Long chatHistoryId;   
}
