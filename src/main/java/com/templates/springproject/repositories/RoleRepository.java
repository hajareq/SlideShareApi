package com.templates.springproject.repositories;

import com.templates.springproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("Select r from role r where name=?1")
    Optional<Role> findByName(String role);
}
