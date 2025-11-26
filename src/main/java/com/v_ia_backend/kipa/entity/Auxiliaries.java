package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_auxiliaries")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auxiliaries implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "auxiliary_id")
    private Long id;

    @Column(name = "auxiliary_number")
    private Long auxiliaryNumber;

    @Column(name = "auxiliary_nit")
    private Long auxiliaryNit;

    @Column(name = "auxiliary_description")
    private String auxiliaryDescription;
    
}