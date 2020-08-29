package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Demande {
    private int idDemande;
    private int idUtilisateur;
    private int idItineraire;
    private String dateDemande;
    private String destination;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
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
    @Column(name = "id_itineraire")
    public int getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(int idItineraire) {
        this.idItineraire = idItineraire;
    }

    @Basic
    @Column(name = "date_demande")
    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    @Basic
    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demande demande = (Demande) o;
        return idDemande == demande.idDemande &&
                idUtilisateur == demande.idUtilisateur &&
                idItineraire == demande.idItineraire &&
                Objects.equals(dateDemande, demande.dateDemande) &&
                Objects.equals(destination, demande.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDemande, idUtilisateur, idItineraire, dateDemande, destination);
    }
}
