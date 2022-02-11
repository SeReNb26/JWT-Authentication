package com.example.jwtauthentication.service.impl;

import org.springframework.stereotype.Service;
import com.example.jwtauthentication.model.Role;
import com.example.jwtauthentication.repository.RoleRepository;
import com.example.jwtauthentication.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByName(name);
    }
}
