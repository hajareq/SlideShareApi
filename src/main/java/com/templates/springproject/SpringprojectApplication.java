package com.templates.springproject;

import com.templates.springproject.entities.Client;
import com.templates.springproject.entities.Role;
import com.templates.springproject.entities.Template;
import com.templates.springproject.repositories.ClientRepository;
import com.templates.springproject.repositories.RoleRepository;
import com.templates.springproject.repositories.TemplateRepository;
import com.templates.springproject.services.TemplateService;
import com.templates.springproject.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class SpringprojectApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringprojectApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringprojectApplication.class);
    }

    @Bean
    CommandLineRunner initDatabase(UserService userService, TemplateService templateService, RoleRepository roleRepository) {
        return args -> {
            Role r = new Role();
            r.setName("client");
            roleRepository.save(r);
            Role r2 = new Role();
            r2.setName("admin");
            roleRepository.save(r2);
            userService.addUser(new Client(0l,"medamine","elalaoui","medamine","amine","amine.elalaoui.med@gmail.com",r));
            userService.addUser(new Client(0l,"oufrid","youness","ghost","1234567","youness.oufrid1@gmail.com",r2));
            templateService.addTemplate(new Template(1l,"Test template",0));
        };
    }

}
