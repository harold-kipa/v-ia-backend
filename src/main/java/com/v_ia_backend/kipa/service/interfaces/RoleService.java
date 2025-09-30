package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Roles;

import java.util.List;

@Service
public interface RoleService {
    Roles getRoleById(Long id);
    List<Roles> getAllRoles();
}
