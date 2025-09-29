package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_status_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusUser implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "status_user_id")
    private Long id;
    
    @Column(name = "status_name")
    private String statusName;
}