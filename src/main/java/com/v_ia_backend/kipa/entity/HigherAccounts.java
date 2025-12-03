package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_higher_accounts_new")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HigherAccounts implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "higher_account_id")
    private Long id;

    @Column(name = "higher_account_number")
    private Long higherAccountNumber;

    @Column(name = "higher_account_description")
    private String higherAccountDescription;
    
}