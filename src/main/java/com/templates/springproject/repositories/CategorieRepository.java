package com.templates.springproject.repositories;

import com.templates.springproject.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

    @Query("select c from Categorie c where c.name = ?1")
    Optional<Categorie> findCategorieByName(String name);
}
