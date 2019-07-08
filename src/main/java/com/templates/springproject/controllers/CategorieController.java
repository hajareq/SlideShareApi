package com.templates.springproject.controllers;

import com.templates.springproject.entities.Categorie;
import com.templates.springproject.entities.User;
import com.templates.springproject.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @PostMapping("/admin/categorie/add")
    public String addCategorie(@RequestBody Categorie categorie) throws JSONException{
        Optional<Categorie> c = categorieService.findCategorieByName(categorie.getName());
        JSONObject response = new JSONObject();
        if(c.isPresent()){
            response.put("error","Categorie already exists");
            return response.toString();
        }
        categorieService.addCategorie(categorie);
        response.put("statut","200");
        response.put("message", "categorie added successfully");
        return response.toString();
    }
}
