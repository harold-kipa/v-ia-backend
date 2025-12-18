package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_entity")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityData implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long id;

    @Column(name = "entity_number")
    private String entityNumber;

    @Column(name = "entity_name")
    private String entityName;
    
}
