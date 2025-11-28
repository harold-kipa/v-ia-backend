package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.CostCenters;

import java.util.List;

@Service
public interface CostCenterService {
    CostCenters getCostCenterById(Long id);
    List<CostCenters> getAllCostCenters();
}
