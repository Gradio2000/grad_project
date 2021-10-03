package com.graduation.project.service;

import com.graduation.project.model.Role;
import com.graduation.project.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole(){
        return roleRepository.findById(1).orElse(null);
    }
}
