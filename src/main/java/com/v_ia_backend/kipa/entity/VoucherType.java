package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_voucher_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoucherType implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "voucher_type_id")
    private Long id;

    @Column(name = "general_number")
    private Long generalNumber;

    @Column(name = "general_description")
    private String generalDescription;

    @Column(name = "specific_number")
    private Long specificNumber;

    @Column(name = "specific_description")
    private String specificDescription;
    
}