package com.templates.springproject.services;

import com.templates.springproject.Interfaces.RoleMetier;
import com.templates.springproject.entities.Role;
import com.templates.springproject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements RoleMetier {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void updateRole(Role role) {
        Role roleToUpdate = roleRepository.getOne(role.getId());
        roleToUpdate.setName(role.getName());
        roleRepository.save(roleToUpdate);
    }

    @Override
    public Optional<Role> findRoleByName(String role) {
        return roleRepository.findByNom(role);
    }
}
