package com.v_ia_backend.kipa.service;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.entity.Roles;
import com.v_ia_backend.kipa.repository.RolesRepositoriy;
import com.v_ia_backend.kipa.service.interfaces.RoleService;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    private final RolesRepositoriy rolesRepositoriy;
    public RoleServiceImpl(RolesRepositoriy rolesRepositoriy) {
        this.rolesRepositoriy = rolesRepositoriy;
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepositoriy.findAll();
    }

    @Override
    public Roles getRoleById(Long id) {
        return rolesRepositoriy.findById(id).orElse(null);
    }
}
