package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.FilesOccired;

import java.util.List;

@Service
public interface FilesOcciredService {
    FilesOccired getFilesOcciredById(Long id);
    List<FilesOccired> getAllFilesOccired();
    // public List<FilesOccired> getFilesOcciredByPaymentsAccountsRelationId(Long id);
}
