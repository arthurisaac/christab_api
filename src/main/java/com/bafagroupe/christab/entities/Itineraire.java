package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Itineraire {
    private int idItineraire;
    private String typeVoyage;
    private Boolean confirmerDepart;
    private Boolean confirmerArrivee;
    private Double latitude;
    private Double longitude;
    private String heureDepart;
    private String heureArrivee;
    private String typeLocalite;
    private String dateDepart;
    private String dateArrivee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itineraire")
    public int getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(int idItineraire) {
        this.idItineraire = idItineraire;
    }

    @Basic
    @Column(name = "type_voyage")
    public String getTypeVoyage() {
        return typeVoyage;
    }

    public void setTypeVoyage(String typeVoyage) {
        this.typeVoyage = typeVoyage;
    }

    @Basic
    @Column(name = "confirmer_depart")
    public Boolean getConfirmerDepart() {
        return confirmerDepart;
    }

    public void setConfirmerDepart(Boolean confirmerDepart) {
        this.confirmerDepart = confirmerDepart;
    }

    @Basic
    @Column(name = "confirmer_arrivee")
    public Boolean getConfirmerArrivee() {
        return confirmerArrivee;
    }

    public void setConfirmerArrivee(Boolean confirmerArrivee) {
        this.confirmerArrivee = confirmerArrivee;
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

    @Basic
    @Column(name = "heure_depart")
    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    @Basic
    @Column(name = "heure_arrivee")
    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    @Basic
    @Column(name = "type_localite")
    public String getTypeLocalite() {
        return typeLocalite;
    }

    public void setTypeLocalite(String typeLocalite) {
        this.typeLocalite = typeLocalite;
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
    @Column(name = "date_arrivee")
    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itineraire that = (Itineraire) o;
        return idItineraire == that.idItineraire &&
                Objects.equals(typeVoyage, that.typeVoyage) &&
                Objects.equals(confirmerDepart, that.confirmerDepart) &&
                Objects.equals(confirmerArrivee, that.confirmerArrivee) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(heureDepart, that.heureDepart) &&
                Objects.equals(heureArrivee, that.heureArrivee) &&
                Objects.equals(typeLocalite, that.typeLocalite) &&
                Objects.equals(dateDepart, that.dateDepart) &&
                Objects.equals(dateArrivee, that.dateArrivee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItineraire, typeVoyage, confirmerDepart, confirmerArrivee, latitude, longitude, heureDepart, heureArrivee, typeLocalite, dateDepart, dateArrivee);
    }
}
