package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_higher_accounts_view")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HigherAccountsView implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "higher_accounts_view_id")
    private Long id;

    @Column(name = "account_number_homologated")
    private Long accountNumberHomologated;

    @Column(name = "account_name_homologated")
    private String accountNameHomologated;
}