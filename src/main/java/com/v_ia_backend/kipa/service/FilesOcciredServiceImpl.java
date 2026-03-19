package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.FilesOccired;
import com.v_ia_backend.kipa.repository.FilesOcciredRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.FilesOcciredService;
import java.util.List;


@Service
public class FilesOcciredServiceImpl implements FilesOcciredService {
    private final FilesOcciredRepositoriy filesOpRepositoriy;
    public FilesOcciredServiceImpl(FilesOcciredRepositoriy FilesOcciredRepositoriy) {
        this.filesOpRepositoriy = FilesOcciredRepositoriy;
    }

    @Override
    public List<FilesOccired> getAllFilesOccired() {
        List<FilesOccired> filesOpList = filesOpRepositoriy.findAll();
        return filesOpList;
    }

    @Override
    public FilesOccired getFilesOcciredById(Long id) {
        return filesOpRepositoriy.findById(id).orElse(null);
    }
    // @Override
    // public List<FilesOccired> getFilesOcciredByPaymentsAccountsRelationId(Long id) {
    //     return filesOpRepositoriy.findByPaymentsAccountsRelationId_Id(id);
    // }
}
