package com.v_ia_backend.kipa.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ChatHistoryRequest {

    @NotEmpty(message = "{notNull.general}")
    private String chatHistoryName;

    // @NotEmpty(message = "{notNull.general}")
    private String tokenChat;

    // @NotEmpty(message = "{notNull.general}")
    private String conversationIdentifier;

    // @NotEmpty(message = "{notNull.general}")
    private Integer userId;   
}
