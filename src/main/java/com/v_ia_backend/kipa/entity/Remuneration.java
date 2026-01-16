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
@Table(name = "tbl_remuneration")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Remuneration implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "remuneration_id")
    private Long id;

    @Column(name = "liquidation_base_income")
    private Long liquidationBaseIncome;

    @Column(name = "compensation_paid_value")
    private Long compensationPaidValue;

    @Column(name = "pending_balance")
    private Long pendingBalance;

    @Column(name = "date", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp movementDate;
    
}
