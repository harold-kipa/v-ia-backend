package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_product_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductType implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_type_id")
    private Long id;

    @Column(name = "product_type_name")
    private String productTypeName;
    
}
