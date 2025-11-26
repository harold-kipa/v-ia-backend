package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_cost_centers")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CostCenters implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cost_center_id")
    private Long id;

    @Column(name = "cost_center_number")
    private Long costCenterNumber;

    @Column(name = "functional_unit")
    private String functionalUnit;

    @Column(name = "sector")
    private String sector;

    @Column(name = "subsector")
    private String subsector;

    @Column(name = "contract_amendments")
    private String contractAmendments;
    
}