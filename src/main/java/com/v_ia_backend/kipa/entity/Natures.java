package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_natures")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Natures implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "nature_id")
    private Long id;

    @Column(name = "nature_description")
    private String natureDescription;
}