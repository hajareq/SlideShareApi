package com.templates.springproject.Interfaces;

import com.templates.springproject.entities.Role;

import java.util.Optional;

public interface RoleMetier {

    void addRole(Role role);
    void deleteRole(Role role);
    void updateRole(Role role);
    Optional<Role> findRoleByName(String role);

}
