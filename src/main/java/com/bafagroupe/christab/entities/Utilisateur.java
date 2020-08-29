package com.bafagroupe.christab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Utilisateur {
    private int idUtilisateur;
    private int idTypeFonction;
    private String email;
    // private String password;
    private String nom;
    private String prenom;
    private Long tel;
    private String photo;
    private String cnib;
    private String numeroCnib;
    private String dateDelivrance;
    private String dateExpiration;
    private String lieuDelivrance;
    private String typeDocument;
    private Boolean afficherTel; // pour afficher le téléphone de l'utilisateur dans les détails s'il autorise
    private Boolean afficherEmail; // pour afficher l'email de l'utilisateur dans les détails s'il autorise
    private Boolean courrierEvaluation; // pour envoyer un courriel à l'utilisateur le rappelant d'évaluer un trajet s'il autorise
    private Boolean courrierPromotion; // pour envoyer un courriel à l'utilisateur l'informant des promotions' d'évaluer un trajet s'il autorise


/*private Collection<Alerte> alertesByIdUtilisateur;
    private Collection<Annonce> annoncesByIdUtilisateur;
    private Collection<Avis> avisByIdUtilisateur;
    private Collection<Demande> demandesByIdUtilisateur;
    private Collection<Engin> enginsByIdUtilisateur;
    private Collection<Paiement> paiementsByIdUtilisateur;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Basic
    @Column(name = "id_type_fonction")
    public int getIdTypeFonction() {
        return idTypeFonction;
    }

    public void setIdTypeFonction(int idTypeFonction) {
        this.idTypeFonction = idTypeFonction;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*@Basic
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
*/
    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "tel")
    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "cnib")
    public String getCnib() {
        return cnib;
    }

    public void setCnib(String cnib) {
        this.cnib = cnib;
    }


    @Column(name = "numero_cnib")
    public String getNumeroCnib() {
        return numeroCnib;
    }

    public void setNumeroCnib(String numeroCnib) {
        this.numeroCnib = numeroCnib;
    }

    @Column(name = "date_delivrance")
    public String getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(String dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    @Column(name = "date_expiration")
    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Column(name = "lieu_delivrance")
    public String getLieuDelivrance() {
        return lieuDelivrance;
    }

    public void setLieuDelivrance(String lieuDelivrance) {
        this.lieuDelivrance = lieuDelivrance;
    }

    @Column(name = "type_document")
    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    @Column(name = "afficher_tel")
    public Boolean isAfficherTel() {
        return afficherTel;
    }

    public void setAfficherTel(Boolean afficherTel) {
        this.afficherTel = afficherTel;
    }

    @Column(name = "afficher_email")
    public Boolean isAfficherEmail() {
        return afficherEmail;
    }

    public void setAfficherEmail(Boolean afficherEmail) {
        this.afficherEmail = afficherEmail;
    }

    @Column(name = "courrier_evaluation")
    public Boolean isCourrierEvaluation() {
        return courrierEvaluation;
    }

    public void setCourrierEvaluation(Boolean courrierEvaluation) {
        this.courrierEvaluation = courrierEvaluation;
    }

    @Column(name = "courrier_promotion")
    public Boolean isCourrierPromotion() {
        return courrierPromotion;
    }

    public void setCourrierPromotion(Boolean courrierPromotion) {
        this.courrierPromotion = courrierPromotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return idUtilisateur == that.idUtilisateur &&
                idTypeFonction == that.idTypeFonction &&
                afficherTel == that.afficherTel &&
                afficherEmail == that.afficherEmail &&
                courrierEvaluation == that.courrierEvaluation &&
                courrierPromotion == that.courrierPromotion &&
                Objects.equals(email, that.email) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(cnib, that.cnib) &&
                Objects.equals(numeroCnib, that.numeroCnib) &&
                Objects.equals(dateDelivrance, that.dateDelivrance) &&
                Objects.equals(dateExpiration, that.dateExpiration) &&
                Objects.equals(lieuDelivrance, that.lieuDelivrance) &&
                Objects.equals(typeDocument, that.typeDocument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idTypeFonction, email, nom, prenom, tel, photo, cnib, numeroCnib, dateDelivrance, dateExpiration, lieuDelivrance, typeDocument, afficherTel, afficherEmail, courrierEvaluation, courrierPromotion);
    }

    /*@JsonIgnore
    @OneToMany(mappedBy = "utilisateurByIdUtilisateur")
    public Collection<Alerte> getAlertesByIdUtilisateur() {
        return alertesByIdUtilisateur;
    }

    public void setAlertesByIdUtilisateur(Collection<Alerte> alertesByIdUtilisateur) {
        this.alertesByIdUtilisateur = alertesByIdUtilisateur;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateurByIdUtilisateur")
    public Collection<Annonce> getAnnoncesByIdUtilisateur() {
        return annoncesByIdUtilisateur;
    }

    public void setAnnoncesByIdUtilisateur(Collection<Annonce> annoncesByIdUtilisateur) {
        this.annoncesByIdUtilisateur = annoncesByIdUtilisateur;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateurByIdUtilisateur")
    public Collection<Avis> getAvisByIdUtilisateur() {
        return avisByIdUtilisateur;
    }

    public void setAvisByIdUtilisateur(Collection<Avis> avisByIdUtilisateur) {
        this.avisByIdUtilisateur = avisByIdUtilisateur;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateurByIdUtilisateur")
    public Collection<Demande> getDemandesByIdUtilisateur() {
        return demandesByIdUtilisateur;
    }

    public void setDemandesByIdUtilisateur(Collection<Demande> demandesByIdUtilisateur) {
        this.demandesByIdUtilisateur = demandesByIdUtilisateur;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateurByIdUtilisateur")
    public Collection<Engin> getEnginsByIdUtilisateur() {
        return enginsByIdUtilisateur;
    }

    public void setEnginsByIdUtilisateur(Collection<Engin> enginsByIdUtilisateur) {
        this.enginsByIdUtilisateur = enginsByIdUtilisateur;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateurByIdUtilisateur")
    public Collection<Paiement> getPaiementsByIdUtilisateur() {
        return paiementsByIdUtilisateur;
    }

    public void setPaiementsByIdUtilisateur(Collection<Paiement> paiementsByIdUtilisateur) {
        this.paiementsByIdUtilisateur = paiementsByIdUtilisateur;
    }*/
}
