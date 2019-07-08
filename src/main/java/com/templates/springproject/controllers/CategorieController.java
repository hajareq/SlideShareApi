package com.templates.springproject.controllers;

import com.templates.springproject.entities.Categorie;
import com.templates.springproject.entities.User;
import com.templates.springproject.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping(value={"/admin/categorie/list","/categorie/list"})
    public List<Categorie> getCategories(){
        return categorieService.findCategories();
    }

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

    @PostMapping("/admin/categorie/delete")
    public String deleteCategorie(@RequestBody Categorie categorie) throws JSONException{
        Optional<Categorie> c = categorieService.findCategorieByName(categorie.getName());
        JSONObject response = new JSONObject();
        if(!c.isPresent()){
            response.put("error","Categorie doesn't exists");
            return response.toString();
        }
        categorieService.deleteCategorie(c.get());
        response.put("statut","200");
        response.put("message", "categorie deleted successfully");
        return response.toString();
    }

    @PostMapping("/admin/categorie/update")
    public String updateCategorie(@RequestBody Categorie categorie) throws JSONException{
        Optional<Categorie> c = categorieService.findCategorieById(categorie.getIdCategorie());
        JSONObject response = new JSONObject();
        if(categorieService.findCategorieByName(categorie.getName()).isPresent()){
            response.put("error","Categorie already exists");
            return response.toString();
        }
        categorieService.updateCategorie(categorie);
        response.put("statut","200");
        response.put("message", "categorie updated successfully");
        return response.toString();
    }
}
