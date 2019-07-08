package com.templates.springproject.controllers;

import com.templates.springproject.entities.Client;
import com.templates.springproject.entities.User;
import com.templates.springproject.repositories.UserRepository;
import com.templates.springproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.Optional;

import static com.templates.springproject.responses.OperationsResponses.sendError;
import static com.templates.springproject.responses.OperationsResponses.sendSuccess;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(){
        userService = new UserService(userRepository);
    }



    @PostMapping("/noauth/client/add")
    public String addUser(@RequestBody Client c) throws JSONException {
        Optional<User> client = userRepository.findUserByUsername(c.getUsername());
        if(client.isPresent()){
            return sendError("Username already exists");
        }
        System.out.println(c.getPassword());
        userService.addUser(c);
        return sendSuccess();
    }

    @PostMapping("/client/update")
    public String updateUser(@RequestBody Client c) throws JSONException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!userService.findUserByUsername(c.getUsername()).isPresent() || c.getUsername().equals(auth.getName())) {
            User appUser = userRepository.findUserByUsername(auth.getName()).get();
            c.setIdUser(appUser.getIdUser());
            if (userService.updateUser(c)) {
                return sendSuccess();
            }
        }
        return sendError("cannot execute the update. Please check your new username or email. it can be used by another user");


    }

}
