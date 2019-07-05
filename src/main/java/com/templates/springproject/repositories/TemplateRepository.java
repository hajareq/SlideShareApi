package com.templates.springproject.repositories;

import com.templates.springproject.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template,Long> {

    @Override
    Optional<Template> findById(Long aLong);

    @Query("select t from Template t where t.nom=:x")
    Optional<Template> findByNom(@Param("x") String nom);

    List<Template> findByListCategories_Intitule(String intitule);
}
