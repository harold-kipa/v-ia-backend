package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "vw_ani_subaccounts_resumen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AniSubaccounts implements Serializable {
    
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "year")
    private int year;

    @Column(name = "subaccount_code_name")
    private String subaccountCodeName;

    @Column(name = "voucher_amount")
    private String voucherAmount;

    @Column(name = "tipo")
    private String type;
    
}
