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
    private String name;

    @ManyToMany(mappedBy = "listCategories")
    private Collection<Template> listTemplates;

    public Categorie() {
    }

    public Categorie(@NotNull Integer idCategorie,@NotNull String name) {
        this.name = name;
        this.idCategorie = idCategorie;
    }

    public Categorie(Integer idCategorie, String name, Collection<Template> listTemplates) {
        this.idCategorie = idCategorie;
        this.name = name;
        this.listTemplates = listTemplates;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Template> getListTemplates() {
        return listTemplates;
    }

    public void setListTemplates(Collection<Template> listTemplates) {
        this.listTemplates = listTemplates;
    }
}
