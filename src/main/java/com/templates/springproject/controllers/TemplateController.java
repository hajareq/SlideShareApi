package com.templates.springproject.controllers;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.templates.springproject.entities.Role;
import com.templates.springproject.entities.Template;
import com.templates.springproject.entities.User;
import com.templates.springproject.repositories.TemplateRepository;
import com.templates.springproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TemplateController {


    @Autowired
    private TemplateRepository repository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/client/templates")
    public List<Template> getTemplates(){
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User appUser = userRepository.findUserByLogin(auth.getName()).get();
        boolean authorised = false;
        for(Role r: appUser.getRole()){
            if(r.getName().equals("client"))
                authorised = true;
        }
        if(!authorised){

        }*/
        return repository.findAll();

    }


}
