package com.templates.springproject.Interfaces;

import com.templates.springproject.entities.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieMetier {

    boolean addCategorie(Categorie categorie);
    boolean updateCategorie(Categorie categorie);
    void deleteCategorie(Categorie categorie);
    Categorie consulterCategorie(Integer id);

    Optional<Categorie> findCategorieByName(String name);
    Optional<Categorie> findCategorieById(Integer id);
    List<Categorie> findCategories();
}
