package com.templates.springproject.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@DiscriminatorValue(value="AD")
public class Admin extends User {


    public Admin() {
        super();
    }

    public Admin(Long idUser, String nom, String prenom, String login, String password, String email) {
        super(idUser, nom, prenom, login, password, email);
    }

    public Admin(Long idUser, String nom, String prenom, String login, String password, String email, Collection<Template> listTemplates) {
        super(idUser, nom, prenom, login, password, email, listTemplates);
    }
}
