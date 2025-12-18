package com.v_ia_backend.kipa.interfase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.v_ia_backend.kipa.entity.Files;

public interface PoContractInterfase {
    Long getId();
    Long getYear();
    Files getFileId();
}
