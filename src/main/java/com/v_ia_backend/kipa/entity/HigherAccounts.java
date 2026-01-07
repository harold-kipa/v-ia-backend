package com.v_ia_backend.kipa.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_higher_accounts_final")
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

    @Column(name = "account_number_homologated")
    private Long accountNumberHomologated;

    @Column(name = "account_name_homologated")
    private String accountNameHomologated;

    @ManyToOne
    @JoinColumn(name = "higher_accounts_view_id")
    private HigherAccountsView higherAccountsViewId;
    
}