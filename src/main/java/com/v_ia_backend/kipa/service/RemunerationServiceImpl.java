package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.response.RemunerationResponse;
import com.v_ia_backend.kipa.entity.Remuneration;
import com.v_ia_backend.kipa.repository.RemunerationRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.RemunerationService;

import java.util.ArrayList;
import java.util.List;


@Service
public class RemunerationServiceImpl implements RemunerationService {
    private final RemunerationRepositoriy remunerationRepositoriy;
    public RemunerationServiceImpl(RemunerationRepositoriy RemunerationRepositoriy) {
        this.remunerationRepositoriy = RemunerationRepositoriy;
    }
    
    @Override
    public List<RemunerationResponse> getAllRemuneration() {
        List<Remuneration> remuneration = remunerationRepositoriy.findAll();
        List<RemunerationResponse> list = new ArrayList<>();

        remuneration.forEach(r -> {
            int anio = r.getMovementDate().toLocalDateTime().getYear();

            RemunerationResponse existing = list.stream()
                .filter(p -> p.getYear() == anio)
                .findFirst()
                .orElse(null);

            long paid = (r.getCompensationPaidValue() == null) ? 0L : r.getCompensationPaidValue();
            long base = (r.getLiquidationBaseIncome() == null) ? 0L : r.getLiquidationBaseIncome();

            if (existing == null) {
                RemunerationResponse created = new RemunerationResponse(anio, paid, base);
                list.add(created); // ✅ solo agregas cuando creas
            } else {
                long paidAcc = (existing.getCompensationPaidValue() == null) ? 0L : existing.getCompensationPaidValue();
                long baseAcc = (existing.getLiquidationBaseIncome() == null) ? 0L : existing.getLiquidationBaseIncome();

                existing.setCompensationPaidValue(paidAcc + paid);
                existing.setLiquidationBaseIncome(baseAcc + base);
                // ❌ NO vuelvas a hacer list.add(existing)
            }
        });

        return list;
    }


    // @Override
    // public List<Remuneration> getAllRemuneration() {
    //     return remunerationRepositoriy.findAll();
    // }

    @Override
    public Remuneration getRemunerationById(Long id) {
        return remunerationRepositoriy.findById(id).orElse(null);
    }
}
