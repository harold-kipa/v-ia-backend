package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "tbl_users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "creation_date", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp creationDate;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "status")
    private StatusUser status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identification_number")
    private Long identificationNumber;
    
}