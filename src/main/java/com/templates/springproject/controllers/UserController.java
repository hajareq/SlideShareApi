package com.templates.springproject.controllers;

import com.templates.springproject.entities.Client;
import com.templates.springproject.entities.User;
import com.templates.springproject.repositories.UserRepository;
import com.templates.springproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private String sendError(String message) throws JSONException{

        JSONObject jo = new JSONObject();
        jo.put("error",message);
        return jo.toString();
    }


    @PostMapping("/client/add")
    public String addUser(@RequestBody Client c) throws JSONException {
        Optional<User> client = userRepository.findUserByUsername(c.getUsername());
        if(client.isPresent()){
            return sendError("Username already exists");
        }
        else{
            UserService user = new UserService(userRepository);
            user.addUser(c);
            JSONObject response = new JSONObject();
            response.put("statut","200");
            response.put("message", "user added successfully");
            return response.toString();
        }
    }

}
