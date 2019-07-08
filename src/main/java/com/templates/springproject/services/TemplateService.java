package com.templates.springproject.services;

import com.templates.springproject.Interfaces.TemplateMetier;
import com.templates.springproject.entities.Categorie;
import com.templates.springproject.entities.Template;
import com.templates.springproject.repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService implements TemplateMetier {

    @Autowired
    private TemplateRepository templateRepository;

    public TemplateService() {
    }

    @Override
    public void addTemplate(Template template) {
        templateRepository.save(template);
    }

    @Override
    public void updateTemplate(Template template) {
        Template templateToUpdate = templateRepository.getOne(template.getIdTemplate());
        templateToUpdate.setNom(template.getNom());
        templateToUpdate.setNbrDownloads(template.getNbrDownloads());

        templateRepository.save(templateToUpdate);
    }

    @Override
    public void deleteTemplate(Template template) {
        templateRepository.delete(template);
    }

    @Override
    public Optional<Template> findByNom(String nom) {
        return templateRepository.findByNom(nom);
    }

    @Override
    public Template findById(Long id) {
        return templateRepository.findById(id).get();
    }

    @Override
    public List<Template> findByCategorie(Categorie categorie) {
        return templateRepository.findByListCategories_Name(categorie.getName());
    }
}
