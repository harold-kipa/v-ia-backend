package com.v_ia_backend.kipa.interfase;

import java.sql.Timestamp;

import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Natures;

public interface MovementsInterfase {
    Long getId();
    Timestamp getMovementDate();
    HigherAccounts getHigherAccountId();
    Auxiliaries getAuxiliaryId();  
    CostCenters getCostCenterId();
    Natures getNatureId();
    String getMovementDescription();
    String getVoucherAmount();
}
