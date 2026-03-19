package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Occired;

import java.util.List;

@Service
public interface OcciredService {
    Occired getOcciredById(Long id);
    List<Occired> getAllOccired();
    public List<Occired> getOcciredByMovementId(Long id);
}
