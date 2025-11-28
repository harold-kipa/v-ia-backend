package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.CostCenters;
import com.v_ia_backend.kipa.repository.CostCentersRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.CostCenterService;

import java.util.List;


@Service
public class CostCenterServiceImpl implements CostCenterService {
    private final CostCentersRepositoriy costCentersRepositoriy;
    public CostCenterServiceImpl(CostCentersRepositoriy CostCentersRepositoriy) {
        this.costCentersRepositoriy = CostCentersRepositoriy;
    }

    @Override
    public List<CostCenters> getAllCostCenters() {
        return costCentersRepositoriy.findAll();
    }

    @Override
    public CostCenters getCostCenterById(Long id) {
        return costCentersRepositoriy.findById(id).orElse(null);
    }
}
