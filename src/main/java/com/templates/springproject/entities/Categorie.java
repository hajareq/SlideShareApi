package com.templates.springproject.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategorie;

    @NotNull
    private String intitule;

    @ManyToMany(mappedBy = "listCategories")
    private Collection<Template> listTemplates;

    public Categorie() {
    }

    public Categorie(Integer idCategorie, String intitule, Collection<Template> listTemplates) {
        this.idCategorie = idCategorie;
        this.intitule = intitule;
        this.listTemplates = listTemplates;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Collection<Template> getListTemplates() {
        return listTemplates;
    }

    public void setListTemplates(Collection<Template> listTemplates) {
        this.listTemplates = listTemplates;
    }
}
