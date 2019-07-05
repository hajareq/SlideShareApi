package com.templates.springproject.Interfaces;

import com.templates.springproject.entities.Categorie;
import com.templates.springproject.entities.Template;

import java.util.List;
import java.util.Optional;

public interface TemplateMetier {
    public void addTemplate(Template template);
    public void updateTemplate (Template template);
    public void deleteTemplate(Template template);
    public Optional<Template> findByNom(String nom);
    public Template findById(Long id);
    public List<Template> findByCategorie(Categorie categorie);
}
