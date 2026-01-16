package com.v_ia_backend.kipa.interfase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.v_ia_backend.kipa.entity.Files;
import com.v_ia_backend.kipa.entity.PoContractStatus;
import com.v_ia_backend.kipa.entity.ProductType;

public interface PoContractInterfase {
    Long getId();
    Long getYear();
    Files getFileId();
    ProductType getProductTypeId();
}
