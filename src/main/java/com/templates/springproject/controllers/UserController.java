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

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(){
        userService = new UserService(userRepository);
    }

    private String sendError(String message) throws JSONException{

        JSONObject jo = new JSONObject();
        jo.put("error",message);
        return jo.toString();
    }


    @PostMapping("/noauth/client/add")
    public String addUser(@RequestBody Client c) throws JSONException {
        Optional<User> client = userRepository.findUserByUsername(c.getUsername());
        if(client.isPresent()){
            return sendError("Username already exists");
        }
        System.out.println(c.getPassword());
        userService.addUser(c);
        JSONObject response = new JSONObject();
        response.put("statut","200");
        response.put("message", "user added successfully");
        return response.toString();
    }

    @PostMapping("/client/update")
    public String updateUser(@RequestBody Client c) throws JSONException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User appUser = userRepository.findUserByUsername(auth.getName()).get();
        if(userService.updateUser(appUser)){
            JSONObject response = new JSONObject();
            response.put("statut","200");
            response.put("message", "user added successfully");
            return response.toString();
        }
        return sendError("cannot execute the update. Please check your new username or email. it can be used by another user");


    }

}
