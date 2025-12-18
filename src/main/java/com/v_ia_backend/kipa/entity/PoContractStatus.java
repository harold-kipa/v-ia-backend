package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_po_contract_status")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoContractStatus implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "po_contract_status_id")
    private Long id;

    @Column(name = "po_contract_status_name")
    private String poContractStatusName;
    
}
