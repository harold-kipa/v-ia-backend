package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_chat_history")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatHistory implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "chat_history_id")
    private Long id;

    @Column(name = "chat_history_name")
    private String chatHistoryName;

    @Column(name = "token_chat")
    private String tokenChat;

    @Column(name = "conversation_identifier")
    private String conversationIdentifier;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users UserId;
}
