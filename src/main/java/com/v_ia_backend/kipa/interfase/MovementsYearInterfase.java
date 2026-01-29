package com.v_ia_backend.kipa.interfase;

import java.sql.Timestamp;

import com.v_ia_backend.kipa.entity.Auxiliaries;
import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.entity.HigherAccounts;
import com.v_ia_backend.kipa.entity.Natures;

public interface MovementsYearInterfase {
    Long getId();
    HigherAccountInterfase getHigherAccountId();
    String getVoucherAmount();
    Timestamp getMovementDate();
}
