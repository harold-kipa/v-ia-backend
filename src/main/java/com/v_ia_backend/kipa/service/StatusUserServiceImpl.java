package com.v_ia_backend.kipa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.StatusUser;
import com.v_ia_backend.kipa.repository.StatusRepository;
import com.v_ia_backend.kipa.service.interfaces.StatusUserService;

@Service
public class StatusUserServiceImpl implements StatusUserService {
    private final StatusRepository statusRepository;
    public StatusUserServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<StatusUser> getAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public StatusUser getStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }
    
}
