package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.response.RemunerationResponse;
import com.v_ia_backend.kipa.entity.Remuneration;

import java.util.List;

@Service
public interface RemunerationService {
    Remuneration getRemunerationById(Long id);
    // List<Remuneration> getAllRemuneration();
    List<RemunerationResponse> getAllRemuneration();
}
