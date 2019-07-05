package com.templates.springproject.Interfaces;

import com.templates.springproject.entities.User;

import java.util.Optional;

public interface UserMetier {
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public User consulterUser(Long idUser);

    public Optional<User> findUserByUsername(String username);
}
