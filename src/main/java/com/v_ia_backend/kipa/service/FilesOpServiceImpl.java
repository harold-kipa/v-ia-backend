package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.FilesOp;
import com.v_ia_backend.kipa.repository.FilesOpRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.FilesOpService;
import java.util.List;


@Service
public class FilesOpServiceImpl implements FilesOpService {
    private final FilesOpRepositoriy filesOpRepositoriy;
    public FilesOpServiceImpl(FilesOpRepositoriy FilesOpRepositoriy) {
        this.filesOpRepositoriy = FilesOpRepositoriy;
    }

    @Override
    public List<FilesOp> getAllFilesOp() {
        List<FilesOp> filesOpList = filesOpRepositoriy.findAll();
        return filesOpList;
    }

    @Override
    public FilesOp getFilesOpById(Long id) {
        return filesOpRepositoriy.findById(id).orElse(null);
    }
    @Override
    public List<FilesOp> getFilesOpByPaymentsAccountsRelationId(Long id) {
        return filesOpRepositoriy.findByPaymentsAccountsRelationId_Id(id);
    }
}
