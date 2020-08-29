package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class Historique {
    private int idHistorique;
    private String user;
    private Integer idEntite;
    private String nomEntite;
    private ZonedDateTime dateOperation;
    private String action;
    private String valeur;
    private Double latitude;
    private Double longitude;
    private String agentWeb;
    private String adresseIp;
    private Boolean actif;
    private Boolean deleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique")
    public int getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    @Basic
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "id_entite")
    public Integer getIdEntite() {
        return idEntite;
    }

    public void setIdEntite(Integer idEntite) {
        this.idEntite = idEntite;
    }

    @Basic
    @Column(name = "nom_entite")
    public String getNomEntite() {
        return nomEntite;
    }

    public void setNomEntite(String nomEntite) {
        this.nomEntite = nomEntite;
    }

    @Basic
    @Column(name = "date_operation")
    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Basic
    @Column(name = "valeur")
    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
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
    @Column(name = "agent_web")
    public String getAgentWeb() {
        return agentWeb;
    }

    public void setAgentWeb(String agentWeb) {
        this.agentWeb = agentWeb;
    }

    @Basic
    @Column(name = "adresse_ip")
    public String getAdresseIp() {
        return adresseIp;
    }

    public void setAdresseIp(String adresseIp) {
        this.adresseIp = adresseIp;
    }

    @Basic
    @Column(name = "actif")
    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Basic
    @Column(name = "deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historique that = (Historique) o;
        return idHistorique == that.idHistorique &&
                Objects.equals(user, that.user) &&
                Objects.equals(idEntite, that.idEntite) &&
                Objects.equals(nomEntite, that.nomEntite) &&
                Objects.equals(dateOperation, that.dateOperation) &&
                Objects.equals(action, that.action) &&
                Objects.equals(valeur, that.valeur) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(agentWeb, that.agentWeb) &&
                Objects.equals(adresseIp, that.adresseIp) &&
                Objects.equals(actif, that.actif) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorique, user, idEntite, nomEntite, dateOperation, action, valeur, latitude, longitude, agentWeb, adresseIp, actif, deleted);
    }
}
