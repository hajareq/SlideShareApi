package com.templates.springproject.services;

import com.templates.springproject.Interfaces.CategorieMetier;
import com.templates.springproject.entities.Categorie;
import com.templates.springproject.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService implements CategorieMetier {

    @Autowired
    private CategorieRepository categorieRepository;


    @Override
    public boolean addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie)!=null;
    }

    @Override
    public boolean updateCategorie(Categorie categorie) {
        Categorie categorieToUpdate = categorieRepository.getOne(categorie.getIdCategorie());
        categorieToUpdate.setName(categorie.getName());

        return categorieRepository.save(categorieToUpdate)!=null;
    }

    @Override
    public void deleteCategorie(Categorie categorie) {
        categorieRepository.delete(categorie);
    }

    @Override
    public Categorie consulterCategorie(Integer id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public Optional<Categorie> findCategorieByName(String name) {
        return categorieRepository.findCategorieByName(name);
    }

    @Override
    public Optional<Categorie> findCategorieById(Integer id) {
        return categorieRepository.findById(id);
    }

    @Override
    public List<Categorie> findCategories() {
        return categorieRepository.findAll();
    }
}
