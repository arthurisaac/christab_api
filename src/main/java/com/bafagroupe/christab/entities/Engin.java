package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Engin {
    private int idEngin;
    private int idUtilisateur;
    private String marque;
    private String modele;
    private String typeEngin;
    private String typeVehicule;
    private Integer anneeModele;
    private String description;
    private String photoEngin;
    private String photoPermis;
    private String photoAssurance;
    private Integer nbrePlace;
    private String carteGrise;
    private String immatriculation;
    private String couleur;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_engin")
    public int getIdEngin() {
        return idEngin;
    }

    public void setIdEngin(int idEngin) {
        this.idEngin = idEngin;
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
    @Column(name = "marque")
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Basic
    @Column(name = "modele")
    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Basic
    @Column(name = "type_engin")
    public String getTypeEngin() {
        return typeEngin;
    }

    public void setTypeEngin(String typeEngin) {
        this.typeEngin = typeEngin;
    }

    @Column(name = "type_vehicule")
    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    @Basic
    @Column(name = "annee_modele")
    public Integer getAnneeModele() {
        return anneeModele;
    }

    public void setAnneeModele(Integer anneeModele) {
        this.anneeModele = anneeModele;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "photo_engin")
    public String getPhotoEngin() {
        return photoEngin;
    }

    public void setPhotoEngin(String photoEngin) {
        this.photoEngin = photoEngin;
    }

    @Basic
    @Column(name = "nbre_place")
    public Integer getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(Integer nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    @Basic
    @Column(name = "carte_grise")
    public String getCarteGrise() {
        return carteGrise;
    }

    public void setCarteGrise(String carteGrise) {
        this.carteGrise = carteGrise;
    }


    @Column(name = "photo_permis")
    public String getPhotoPermis() {
        return photoPermis;
    }

    public void setPhotoPermis(String photoPermis) {
        this.photoPermis = photoPermis;
    }


    @Column(name = "photo_assurance")
    public String getPhotoAssurance() {
        return photoAssurance;
    }

    public void setPhotoAssurance(String photoAssurance) {
        this.photoAssurance = photoAssurance;
    }


    @Column(name = "immatriculation")
    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }


    @Column(name = "couleur")
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engin engin = (Engin) o;
        return idEngin == engin.idEngin &&
                idUtilisateur == engin.idUtilisateur &&
                Objects.equals(marque, engin.marque) &&
                Objects.equals(modele, engin.modele) &&
                Objects.equals(typeEngin, engin.typeEngin) &&
                Objects.equals(typeVehicule, engin.typeVehicule) &&
                Objects.equals(anneeModele, engin.anneeModele) &&
                Objects.equals(description, engin.description) &&
                Objects.equals(photoEngin, engin.photoEngin) &&
                Objects.equals(photoPermis, engin.photoPermis) &&
                Objects.equals(photoAssurance, engin.photoAssurance) &&
                Objects.equals(nbrePlace, engin.nbrePlace) &&
                Objects.equals(carteGrise, engin.carteGrise) &&
                Objects.equals(immatriculation, engin.immatriculation) &&
                Objects.equals(couleur, engin.couleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEngin, idUtilisateur, marque, modele, typeEngin, typeVehicule, anneeModele, description, photoEngin, photoPermis, photoAssurance, nbrePlace, carteGrise, immatriculation, couleur);
    }
}
