package com.templates.springproject.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;


@Entity
public class Template implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemplate;

    @NotNull
    private String nom;

    private int nbrDownloads;

    @ManyToMany
    private Collection<User> listUsers;

    @ManyToMany
    private Collection<Categorie> listCategories;

    private String path;

    public Template() {
    }

    public Template(Long idTemplate, String nom, int nbrDownloads) {
        this.idTemplate = idTemplate;
        this.nom = nom;
        this.nbrDownloads = nbrDownloads;
    }

    public Template(Long idTemplate, String nom, int nbrDownloads, Collection<User> listUsers, Collection<Categorie> listCategories) {
        this.idTemplate = idTemplate;
        this.nom = nom;
        this.nbrDownloads = nbrDownloads;
        this.listUsers = listUsers;
        this.listCategories=listCategories;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Long idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbrDownloads() {
        return nbrDownloads;
    }

    public void setNbrDownloads(int nbrDownloads) {
        this.nbrDownloads = nbrDownloads;
    }

    public Collection<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Collection<User> listUsers) {
        this.listUsers = listUsers;
    }

    public Collection<Categorie> getListCategories() {
        return listCategories;
    }

    public void setListCategories(Collection<Categorie> listCategories) {
        this.listCategories = listCategories;
    }
}
