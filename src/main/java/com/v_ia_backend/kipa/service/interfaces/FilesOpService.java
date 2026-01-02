package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.FilesOp;

import java.util.List;

@Service
public interface FilesOpService {
    FilesOp getFilesOpById(Long id);
    List<FilesOp> getAllFilesOp();
    public List<FilesOp> getFilesOpByPaymentsAccountsRelationId(Long id);
}
