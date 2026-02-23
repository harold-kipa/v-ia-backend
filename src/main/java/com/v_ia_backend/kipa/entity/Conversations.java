package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_conversations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Conversations implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "conversations_id")
    private Long id;

    @Column(name = "from_user")
    private String from;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "chat_history_id")
    private ChatHistory chatHistoryId;
}
