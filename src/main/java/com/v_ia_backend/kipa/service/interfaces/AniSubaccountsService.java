package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.response.AniSubaccountsResponse;
import com.v_ia_backend.kipa.entity.AniSubaccounts;

import java.util.List;

@Service
public interface AniSubaccountsService {
    List<AniSubaccounts> getAllAniSubaccountsProject();
    List<AniSubaccounts> getAllAniSubaccountsAni();
}
