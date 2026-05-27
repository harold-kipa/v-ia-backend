package com.v_ia_backend.kipa.interfase;

import java.sql.Timestamp;

public interface MovementsYearInterfase {
    Long getId();
    HigherAccountInterfase getHigherAccountId();
    String getVoucherAmount();
    Timestamp getMovementDate();
}
