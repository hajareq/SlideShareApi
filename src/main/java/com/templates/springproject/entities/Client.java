package com.templates.springproject.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Set;

@Entity
@DiscriminatorValue(value="CL")
public class Client extends User {

    public Client() {
        super();
    }

    public Client(Long idUser, String nom, String prenom, String login, String password, String email) {
        super(idUser, nom, prenom, login, password, email);
    }

    public Client(Long idUser, String nom, String prenom, String login, String password, String email, Set<Role> roles) {
        super(idUser, nom, prenom, login, password, email);
        super.setListRoles(roles);
    }

    public Client(Long idUser, String nom, String prenom, String login, String password, String email, Collection<Template> listTemplates) {
        super(idUser, nom, prenom, login, password, email, listTemplates);
    }
}
