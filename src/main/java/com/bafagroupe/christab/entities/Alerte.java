package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Alerte {
    private int idAlerte;
    private int idUtilisateur;
    private String motifAlerte;
    private String dateAlerte;
    private Long nbreAlerte;
    private Double latitude;
    private Double longitude;
    private int idAnnonce;
    private Boolean rembourser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerte")
    public int getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(int idAlerte) {
        this.idAlerte = idAlerte;
    }

    @Basic
    @Column(name = "id_utilisateur")
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Column(name = "id_annonce")
    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    @Basic
    @Column(name = "motif_alerte")
    public String getMotifAlerte() {
        return motifAlerte;
    }

    public void setMotifAlerte(String motifAlerte) {
        this.motifAlerte = motifAlerte;
    }

    @Basic
    @Column(name = "date_alerte")
    public String getDateAlerte() {
        return dateAlerte;
    }

    public void setDateAlerte(String dateAlerte) {
        this.dateAlerte = dateAlerte;
    }

    @Basic
    @Column(name = "nbre_alerte")
    public Long getNbreAlerte() {
        return nbreAlerte;
    }

    public void setNbreAlerte(Long nbreAlerte) {
        this.nbreAlerte = nbreAlerte;
    }

    @Basic
    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean isRembourser() {
        return rembourser;
    }

    public void setRembourser(Boolean rembourser) {
        this.rembourser = rembourser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alerte alerte = (Alerte) o;
        return idAlerte == alerte.idAlerte &&
                idUtilisateur == alerte.idUtilisateur &&
                idAnnonce == alerte.idAnnonce &&
                rembourser == alerte.rembourser &&
                Objects.equals(motifAlerte, alerte.motifAlerte) &&
                Objects.equals(dateAlerte, alerte.dateAlerte) &&
                Objects.equals(nbreAlerte, alerte.nbreAlerte) &&
                Objects.equals(latitude, alerte.latitude) &&
                Objects.equals(longitude, alerte.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlerte, idUtilisateur, motifAlerte, dateAlerte, nbreAlerte, latitude, longitude, idAnnonce, rembourser);
    }
}
