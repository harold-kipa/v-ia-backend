package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.PoContract;
import com.v_ia_backend.kipa.repository.PoContractRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.PoContractService;
import com.v_ia_backend.kipa.interfase.PoContractInterfase;

import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;


@Service
public class PoContractServiceImpl implements PoContractService {
    private final PoContractRepositoriy poContractRepositoriy;
    public PoContractServiceImpl(PoContractRepositoriy PoContractRepositoriy) {
        this.poContractRepositoriy = PoContractRepositoriy;
    }

    @Override
    public List<PoContractInterfase> getAllPoContract() {
        List<PoContractInterfase> poContractList = poContractRepositoriy.findAllDistinct();
        return poContractList;
    }

    @Override
    public PoContract getPoContractById(Long id) {
        return poContractRepositoriy.findById(id).orElse(null);
    }

    @Override
    public List<PoContract> getPoContractByConsecutiveAndYear(Long id) {
        PoContract poContract = this.getPoContractById(id);
        return poContractRepositoriy.findByConsecutiveAndYear(poContract.getConsecutive(), poContract.getYear());
    }

    @Override
    public List<PoContract> getPoContractByMovementId(Long id) {
        List<PoContract> poContractList = poContractRepositoriy.findByMovementId_Id(id);

        if (poContractList == null || poContractList.isEmpty()) {
            return new ArrayList<>();
        }

        for (PoContract poContract : poContractList) {
            if (poContract.getFileId() != null && poContract.getFileId().getFileUrl() != null) {
                String url = poContract.getFileId().getFileUrl().trim()
                                  .replace(" ", "%20");

                try (InputStream is = new URL(url.trim()).openStream()) {
                    byte[] pdfBytes = is.readAllBytes();
                    poContract.getFileId().setFileUrl(Base64.getEncoder().encodeToString(pdfBytes));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return poContractList;
    }
}
