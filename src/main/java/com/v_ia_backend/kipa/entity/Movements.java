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
@Table(name = "tbl_movements_final_final")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movements implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_code_id")
    private Subaccounts companyCodeId;

    @Column(name = "company_description")
    private String companyDescription;

    @Column(name = "movement_date", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp movementDate;

    @ManyToOne
    @JoinColumn(name = "voucher_type_id")
    private VoucherType voucherTypeId;

    @Column(name = "voucher_number")
    private Long voucherNumber;

    @ManyToOne
    @JoinColumn(name = "higher_account_id")
    private HigherAccounts higherAccountId;

    @Column(name = "movement_description")
    private String movementDescription;

    @ManyToOne
    @JoinColumn(name = "auxiliary_id")
    private Auxiliaries auxiliaryId;

    @Column(name = "status")
    private String status;

    @Column(name = "voucher_amount")
    private String voucherAmount;

    @ManyToOne
    @JoinColumn(name = "nature_id")
    private Natures natureId;

    @ManyToOne
    @JoinColumn(name = "cost_center_id")
    private CostCenters costCenterId;

    @Column(name = "foreign_currency_amount")
    private String foreignCurrencyAmount;

    @Column(name = "currency")
    private String currency;

    @ManyToOne
    @JoinColumn(name = "po_contract_id")
    private PoContract poContractId;

    @ManyToOne
    @JoinColumn(name = "payments_accounts_relation_id")
    private PaymentsAccountsRelation paymentsAccountsRelationId;

}