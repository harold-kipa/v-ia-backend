package com.v_ia_backend.kipa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Getter
@Setter
@Table(name = "tbl_occired")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Occired implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "occired_id")
    private Long id;

    @Column(name = "origin_product")
    private String OriginProduct;

    @Column(name = "description")
    private String description;

    @Column(name = "destination_product")
    private String destinatioProduct;

    @Column(name = "transaction_number")
    private String transactionNumber;

    @Column(name = "payment_date", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paymentDate;
    
    @Column(name = "voucher_amount")
    private String voucherAmount;

    @Column(name = "commission")
    private String commission;
    
    @Column(name = "iva")
    private String iva;

    @Column(name = "voucher_number")
    private String voucherNumber;
    
    @ManyToOne
    @JoinColumn(name = "auxiliary_id")
    private Auxiliaries auxiliariesId;

    @ManyToOne
    @JoinColumn(name = "movement_id")
    private Movements movementId;

    @ManyToOne
    @JoinColumn(name = "file_occired_id")
    private FilesOccired fileId;
}
