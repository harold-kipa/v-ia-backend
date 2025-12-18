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
@Table(name = "tbl_payments_accounts_relation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentsAccountsRelation implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "payments_accounts_relation_id")
    private Long id;

    @Column(name = "creation_date", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp creationDate;
    
    @Column(name = "entity_name")
    private String consecutiveNumber;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_date_2")
    private String paymentDate2;

    @Column(name = "payment_date_3")
    private String paymentDate3;

    @Column(name = "payment_value")
    private String paymentValue;

    @Column(name = "payment_value_2")
    private String paymentValue2;

    @Column(name = "payment_value_3")
    private String paymentValue3;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "subtotal")
    private String subtotal;

    @Column(name = "iva")
    private String iva;

    @Column(name = "consumption_tax")
    private String consumptionTax;

    @Column(name = "gross_value")
    private String grossValue;

    @Column(name = "withholding_tax")
    private String withholdingTax;

    @Column(name = "withholding_iva")
    private String withholdingIva;

    @Column(name = "withholding_ica")
    private String withholdingIca;

    @Column(name = "net_value")
    private String netValue;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethodId;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityData entityId;

    @ManyToOne
    @JoinColumn(name = "auxiliary_id")
    private Auxiliaries auxiliaryId;
}