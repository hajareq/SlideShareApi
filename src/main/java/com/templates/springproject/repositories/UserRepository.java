package com.templates.springproject.repositories;

import com.templates.springproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(" select u from User u " +
            " where u.username = ?1")
    Optional<User> findUserByLogin(String login);

}
