package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_payment_method")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethod implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Long id;

    @Column(name = "payment_method_name")
    private String paymentMethodName;
    
}
