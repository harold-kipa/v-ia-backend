package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Occired;
import com.v_ia_backend.kipa.repository.OcciredRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.OcciredService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import java.util.Base64;


@Service
public class OcciredServiceImpl implements OcciredService {
    private final OcciredRepositoriy filesOpRepositoriy;
    public OcciredServiceImpl(OcciredRepositoriy OcciredRepositoriy) {
        this.filesOpRepositoriy = OcciredRepositoriy;
    }

    @Override
    public List<Occired> getAllOccired() {
        List<Occired> filesOpList = filesOpRepositoriy.findAll();
        return filesOpList;
    }

    @Override
    public Occired getOcciredById(Long id) {
        return filesOpRepositoriy.findById(id).orElse(null);
    }
    @Override
    public List<Occired> getOcciredByMovementId(Long id) {
        Occired occired = filesOpRepositoriy.findByMovementId_Id(id).get(0);
        String url = occired.getFileId().getFileUrl();

        try (InputStream is = new URL(url.trim()).openStream()) {
            byte[] pdfBytes = is.readAllBytes();
            occired.getFileId().setFileUrl(Base64.getEncoder().encodeToString(pdfBytes));

        } catch (IOException e) {
        }
        List<Occired> occiredList = new java.util.ArrayList<>();
        occiredList.add(occired);
        return occiredList;
    }
}
