package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_arh_clasification")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArhClasification implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "arh_clasification_id")
    private Long id;

    @Column(name = "arh_clasification_name")
    private String arhClasificationName;
}