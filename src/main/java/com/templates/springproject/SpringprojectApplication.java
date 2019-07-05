package com.templates.springproject;

import com.templates.springproject.entities.Client;
import com.templates.springproject.entities.Role;
import com.templates.springproject.entities.Template;
import com.templates.springproject.repositories.ClientRepository;
import com.templates.springproject.repositories.RoleRepository;
import com.templates.springproject.repositories.TemplateRepository;
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
    CommandLineRunner initDatabase(ClientRepository repository, TemplateRepository tempRepo, RoleRepository roleRepository) {
        return args -> {
            Role r = new Role();
            r.setName("client");
            roleRepository.save(r);
            repository.save(new Client(1l,"medamine","elalaoui","medamine","$2y$10$WM5b5Ac5zlDPKRO1l9ZXDe3hdhGS/rx7okx1cigXr6XnhRbFWxub2","amine.elalaoui.med@gmail.com",r));
            tempRepo.save(new Template(1l,"Test template",0));
        };
    }

}
