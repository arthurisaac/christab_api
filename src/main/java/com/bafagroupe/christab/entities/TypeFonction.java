package com.bafagroupe.christab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Type_Fonction", schema = "christaB_db")
public class TypeFonction {
    private short idTypeFonction;
    private String libelleTypeFonction;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_fonction")
    public short getIdTypeFonction() {
        return idTypeFonction;
    }

    public void setIdTypeFonction(short idTypeFonction) {
        this.idTypeFonction = idTypeFonction;
    }

    @Basic
    @Column(name = "libelle_type_fonction")
    public String getLibelleTypeFonction() {
        return libelleTypeFonction;
    }

    public void setLibelleTypeFonction(String libelleTypeFonction) {
        this.libelleTypeFonction = libelleTypeFonction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeFonction that = (TypeFonction) o;
        return idTypeFonction == that.idTypeFonction &&
                Objects.equals(libelleTypeFonction, that.libelleTypeFonction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeFonction, libelleTypeFonction);
    }
}
