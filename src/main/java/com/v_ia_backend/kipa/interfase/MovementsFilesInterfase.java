package com.v_ia_backend.kipa.interfase;

import java.sql.Timestamp;

import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Natures;
import com.v_ia_backend.kipa.entity.PaymentsAccountsRelation;

public interface MovementsFilesInterfase {
    Long getId();
    Long getPaymentsAccountsRelationId_Id();
    String getPoContractId_FileId_FileUrl();
}
