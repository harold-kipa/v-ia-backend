package com.v_ia_backend.kipa.service.interfaces;

import java.util.List;

import com.v_ia_backend.kipa.entity.StatusUser;

public interface StatusUserService {

    StatusUser getStatusById(Long id);
    List<StatusUser> getAllStatus();
    
}
