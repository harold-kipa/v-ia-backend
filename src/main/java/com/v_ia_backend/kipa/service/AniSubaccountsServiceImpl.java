package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.response.AniSubaccountsResponse;
import com.v_ia_backend.kipa.entity.AniSubaccounts;
import com.v_ia_backend.kipa.repository.AniSubaccountsRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.AniSubaccountsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class AniSubaccountsServiceImpl implements AniSubaccountsService {
    private final AniSubaccountsRepositoriy aniSubaccountsRepositoriy;
    public AniSubaccountsServiceImpl(AniSubaccountsRepositoriy AniSubaccountsRepositoriy) {
        this.aniSubaccountsRepositoriy = AniSubaccountsRepositoriy;
    }

    @Override
    public List<AniSubaccounts> getAllAniSubaccountsProject() {
        List<AniSubaccounts> aniSubaccountsList = aniSubaccountsRepositoriy.findByType("proyecto");
        // List<AniSubaccountsResponse> aniSubaccountsListFinal = new ArrayList<>();
        
        // aniSubaccountsList.forEach(aniSubaccounts -> {
        //     AniSubaccountsResponse aniSubaccountsResponse = aniSubaccountsListFinal.stream().filter(p -> p.getSubaccountCodeName().equals(aniSubaccounts.getSubaccountCodeName())).findFirst().orElse(null);
        //     if(aniSubaccountsResponse == null){
        //         aniSubaccountsResponse = new AniSubaccountsResponse();
        //         aniSubaccountsResponse.setSubaccountCodeName(aniSubaccounts.getSubaccountCodeName());
        //         aniSubaccountsResponse.setVoucherAmount(stringToLong(aniSubaccounts.getVoucherAmount()));
        //         aniSubaccountsResponse.setYear(aniSubaccounts.getYear());
        //         aniSubaccountsListFinal.add(aniSubaccountsResponse);
        //     }
        //     else{
        //         aniSubaccountsResponse.setVoucherAmount(aniSubaccountsResponse.getVoucherAmount().add(stringToLong((aniSubaccounts.getVoucherAmount()))));
        //     }
        // });
        return aniSubaccountsList;
    }

    @Override
    public List<AniSubaccounts> getAllAniSubaccountsAni() {
        List<AniSubaccounts> aniSubaccountsList = aniSubaccountsRepositoriy.findByType("ANI");
        // List<AniSubaccountsResponse> aniSubaccountsListFinal = new ArrayList<>();
        
        // aniSubaccountsList.forEach(aniSubaccounts -> {
        //     AniSubaccountsResponse aniSubaccountsResponse = aniSubaccountsListFinal.stream().filter(p -> p.getSubaccountCodeName().equals(aniSubaccounts.getSubaccountCodeName()) && p.getYear() == (aniSubaccounts.getYear())).findFirst().orElse(null);
        //     if(aniSubaccountsResponse == null){
        //         aniSubaccountsResponse = new AniSubaccountsResponse();
        //         aniSubaccountsResponse.setSubaccountCodeName(aniSubaccounts.getSubaccountCodeName());
        //         aniSubaccountsResponse.setVoucherAmount(stringToLong(aniSubaccounts.getVoucherAmount()));
        //         aniSubaccountsResponse.setYear(aniSubaccounts.getYear());
        //         aniSubaccountsListFinal.add(aniSubaccountsResponse);
        //     }
        //     else{
        //         aniSubaccountsResponse.setVoucherAmount(aniSubaccountsResponse.getVoucherAmount().add(stringToLong((aniSubaccounts.getVoucherAmount()))));
        //     }
        // });
        return aniSubaccountsList;
    }


    public BigDecimal stringToLong(String raw){
        if(raw != null && !raw.isBlank()){
            // Normalize: remove grouping separators and trim
            String cleaned = raw.replaceAll("[,\\s]", "");
            try{
                return new java.math.BigDecimal(cleaned);
            } catch (NumberFormatException ex){
                // If parsing fails, fallback to zero to avoid crashing; log the problem
                System.err.println("Failed parsing voucherAmount");
                return java.math.BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;

    }

}
