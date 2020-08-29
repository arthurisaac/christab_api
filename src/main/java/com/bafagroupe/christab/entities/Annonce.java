package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Annonce {
    private int idAnnonce;
    private int idItineraire;
    private int idUtilisateur;
    private int idEngin;
    private String libelleAnnonce;
    private String dateAnnonce;
    private String dateDepart;
    private String depart;
    private String destination;
    private String lieuDepart;
    private String lieuArrivee;
    private Integer nbrePersonne;
    private String plagePrix;
    private Integer prix;
    private Integer prixReservation;
    private Integer totalPrix;
    private Boolean publier;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annonce")
    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
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
    @Column(name = "id_utilisateur")
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Column(name = "id_engin")
    public int getIdEngin() {
        return idEngin;
    }

    public void setIdEngin(int idEngin) {
        this.idEngin = idEngin;
    }

    @Basic
    @Column(name = "libelle_annonce")
    public String getLibelleAnnonce() {
        return libelleAnnonce;
    }

    public void setLibelleAnnonce(String libelleAnnonce) {
        this.libelleAnnonce = libelleAnnonce;
    }

    @Basic
    @Column(name = "date_annonce")
    public String getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(String dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    @Basic
    @Column(name = "date_depart")
    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    @Basic
    @Column(name = "depart")
    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    @Basic
    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "lieu_depart")
    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    @Column(name = "lieu_arrivee")
    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    @Basic
    @Column(name = "nbre_personne")
    public Integer getNbrePersonne() {
        return nbrePersonne;
    }

    public void setNbrePersonne(Integer nbrePersonne) {
        this.nbrePersonne = nbrePersonne;
    }

    @Basic
    @Column(name = "plage_prix")
    public String getPlagePrix() {
        return plagePrix;
    }

    public void setPlagePrix(String plagePrix) {
        this.plagePrix = plagePrix;
    }

    @Column(name = "prix")
    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    @Column(name = "prix_reservation")
    public Integer getPrixReservation() {
        return prixReservation;
    }

    public void setPrixReservation(Integer prixReservation) {
        this.prixReservation = prixReservation;
    }

    @Column(name = "total_prix")
    public Integer getTotalPrix() {
        return totalPrix;
    }

    public void setTotalPrix(Integer totalPrix) {
        this.totalPrix = totalPrix;
    }

    @Basic
    @Column(name = "publier")
    public Boolean getPublier() {
        return publier;
    }

    public void setPublier(Boolean publier) {
        this.publier = publier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Annonce annonce = (Annonce) o;
        return idAnnonce == annonce.idAnnonce &&
                idItineraire == annonce.idItineraire &&
                idUtilisateur == annonce.idUtilisateur &&
                idEngin == annonce.idEngin &&
                Objects.equals(libelleAnnonce, annonce.libelleAnnonce) &&
                Objects.equals(dateAnnonce, annonce.dateAnnonce) &&
                Objects.equals(dateDepart, annonce.dateDepart) &&
                Objects.equals(depart, annonce.depart) &&
                Objects.equals(destination, annonce.destination) &&
                Objects.equals(lieuDepart, annonce.lieuDepart) &&
                Objects.equals(lieuArrivee, annonce.lieuArrivee) &&
                Objects.equals(nbrePersonne, annonce.nbrePersonne) &&
                Objects.equals(plagePrix, annonce.plagePrix) &&
                Objects.equals(prix, annonce.prix) &&
                Objects.equals(prixReservation, annonce.prixReservation) &&
                Objects.equals(totalPrix, annonce.totalPrix) &&
                Objects.equals(publier, annonce.publier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnnonce, idItineraire, idUtilisateur, idEngin, libelleAnnonce, dateAnnonce, dateDepart, depart, destination, lieuDepart, lieuArrivee, nbrePersonne, plagePrix, prix, prixReservation, totalPrix, publier);
    }
}
