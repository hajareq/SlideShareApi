package com.templates.springproject.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = { "username" }))
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_USER",discriminatorType=DiscriminatorType.STRING,length=2)
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nom;
    private String prenom;
    @NotNull
    @Size(min = 4, max = 15)
    private String username;
    @NotNull
    @Size(min=6,message="la taille doit être supérieure à 6")
    private String password;
    @NotNull
    @Email
    private String email;

    @ManyToMany(mappedBy = "listUsers")
    private Collection<Template> listTemplates;

    @ManyToOne
    private Role role;

    public User() {
    }

    public User(Long idUser, String nom, String prenom, String login, String password, String email) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.username = login;
        this.password = password;
        this.email = email;
    }

    public User(Long idUser, String nom, String prenom, String login, String password, String email, Collection<Template> listTemplates) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.username = login;
        this.password = password;
        this.email = email;
        this.listTemplates = listTemplates;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role listRoles) {
        this.role = listRoles;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Template> getListTemplates() {
        return listTemplates;
    }

    public void setListTemplates(Collection<Template> listTemplates) {
        this.listTemplates = listTemplates;
    }
}
