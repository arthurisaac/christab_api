package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Avis {
    private int idAvis;
    private int idUtilisateur;
    private String dateAvis;
    private String avis;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avis")
    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    @Basic
    @Column(name = "id_utilisateur")
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Basic
    @Column(name = "date_avis")
    public String getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(String dateAvis) {
        this.dateAvis = dateAvis;
    }

    @Basic
    @Column(name = "avis")
    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avis avis1 = (Avis) o;
        return idAvis == avis1.idAvis &&
                idUtilisateur == avis1.idUtilisateur &&
                Objects.equals(dateAvis, avis1.dateAvis) &&
                Objects.equals(avis, avis1.avis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAvis, idUtilisateur, dateAvis, avis);
    }
}
