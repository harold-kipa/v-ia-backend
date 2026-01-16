package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_po_contract_final")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoContract implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "po_contract_id")
    private Long id;

    @Column(name = "year")
    private Long year;

    @ManyToOne
    @JoinColumn(name = "po_contract_status_id")
    private PoContractStatus poContractStatusId;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private Files fileId;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productTypeId;

    @Column(name = "description")
    private String description;

    @Column(name = "execution_place")
    private String executionPlace;

    @ManyToOne
    @JoinColumn(name = "supplier_type_id")
    private SupplierType supplierTypeId;

    @ManyToOne
    @JoinColumn(name = "auxiliary_id")
    private Auxiliaries auxiliaryId;

}