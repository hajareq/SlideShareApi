package com.templates.springproject.Interfaces;

import com.templates.springproject.entities.User;

import java.util.Optional;

public interface UserMetier {
     void addUser(User user);
     boolean updateUser(User user);
     void deleteUser(User user);
     User consulterUser(Long idUser);

     Optional<User> findUserByUsername(String username);
}
