package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Paiement {
    private int idPaiement;
    private int idUtilisateur;
    private Long numeroClient;
    private int codeOtp;
    private Long montantPaiement;
    private String datePaiement;
    private String heurePaiement;
    private int numeroPaiement;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
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
    @Column(name = "numero_client")
    public Long getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(Long numeroClient) {
        this.numeroClient = numeroClient;
    }

    @Basic
    @Column(name = "code_otp")
    public int getCodeOtp() {
        return codeOtp;
    }

    public void setCodeOtp(int codeOtp) {
        this.codeOtp = codeOtp;
    }

    @Basic
    @Column(name = "montant_paiement")
    public Long getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(Long montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    @Basic
    @Column(name = "date_paiement")
    public String getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(String datePaiement) {
        this.datePaiement = datePaiement;
    }


    @Column(name = "heure_paiement")
    public String getHeurePaiement() {
        return heurePaiement;
    }

    public void setHeurePaiement(String heurePaiement) {
        this.heurePaiement = heurePaiement;
    }

    @Column(name = "numero_paiement")
    public int getNumeroPaiement() {
        return numeroPaiement;
    }

    public void setNumeroPaiement(int numeroPaiement) {
        this.numeroPaiement = numeroPaiement;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paiement paiement = (Paiement) o;
        return idPaiement == paiement.idPaiement &&
                idUtilisateur == paiement.idUtilisateur &&
                codeOtp == paiement.codeOtp &&
                numeroPaiement == paiement.numeroPaiement &&
                Objects.equals(numeroClient, paiement.numeroClient) &&
                Objects.equals(montantPaiement, paiement.montantPaiement) &&
                Objects.equals(datePaiement, paiement.datePaiement) &&
                Objects.equals(heurePaiement, paiement.heurePaiement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaiement, idUtilisateur, numeroClient, codeOtp, montantPaiement, datePaiement, heurePaiement, numeroPaiement);
    }
}
